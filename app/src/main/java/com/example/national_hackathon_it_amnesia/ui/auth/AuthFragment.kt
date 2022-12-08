package com.example.national_hackathon_it_amnesia.ui.auth

import android.annotation.SuppressLint
import android.hardware.fingerprint.FingerprintManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.R
import com.example.national_hackathon_it_amnesia.domain.sharedPreferences.SharedPreferences
import com.example.national_hackathon_it_amnesia.mvp.BaseFragment
import com.example.national_hackathon_it_amnesia.utils.navigateTo
import com.multidots.fingerprintauth.FingerPrintAuthCallback
import com.multidots.fingerprintauth.FingerPrintAuthHelper
import kotlinx.android.synthetic.main.auth_fragment.*
import javax.inject.Inject


class AuthFragment: BaseFragment<AuthPresenterImpl>(), AuthView, FingerPrintAuthCallback {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var fingerprintHelper: FingerPrintAuthHelper

    var isNavigating = false

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createAuthFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        activity?.window?.statusBarColor = resources.getColor(R.color.white, null)
        fingerprintHelper = FingerPrintAuthHelper.getHelper(requireContext(), this)
        //helloTextView.text = resources.getString(R.string.GoodMorning, sharedPreferences.userName)
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        pinView.setOnPinKeyClickListener = { keyPressed ->
            if (keyPressed == "fingerprint") fingerprintHelper.startAuth()
        }
        pinView.setOnCompletedListener = { pinCode ->
            when {
                (pinCode == sharedPreferences.pinCode) -> {
                    isNavigating = true
                    findNavController().navigateTo(findNavController(), R.id.action_authFragment_to_chatFragment, true)
                }
                else -> pinView.showError(true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isNavigating = false
        fingerprintHelper.startAuth()
    }

    override fun onPause() {
        super.onPause()
        fingerprintHelper.stopAuth()
    }

    @SuppressLint("SetTextI18n")
    override fun onNoFingerPrintHardwareFound() {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.FingerprintNotSupported), Toast.LENGTH_SHORT).show()
    }

    override fun onAuthFailed(errorCode: Int, errorMessage: String?) {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.AuthFailed), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onNoFingerPrintRegistered() {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.NoFingerprintRegistered), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onBelowMarshmallow() {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.FingerprintNotAvailableOnThisOS), Toast.LENGTH_SHORT).show()
    }

    override fun onAuthSuccess(cryptoObject: FingerprintManager.CryptoObject?) {
        Toast.makeText(requireContext(), getString(R.string.AuthenticationSuccess), Toast.LENGTH_SHORT).show()
            isNavigating = true
            findNavController().navigateTo(findNavController(),
                R.id.action_authFragment_to_chatFragment, true)
    }

    override fun onBackPressed() {
        MaterialDialog.Builder(requireContext())
            .content(getString(R.string.ExitConfirm))
            .positiveText(R.string.Yes)
            .contentColor(resources.getColor(R.color.black, null))
            .positiveColor(resources.getColor(R.color.mainColor, null))
            .negativeColor(resources.getColor(R.color.black, null))
            .negativeText(R.string.No)
            .onPositive { materialDialog, _ ->
                materialDialog.dismiss()
                requireActivity().finish()
            }
            .onNegative { materialDialog, _ ->
                materialDialog.dismiss()
            }.show()
    }
}