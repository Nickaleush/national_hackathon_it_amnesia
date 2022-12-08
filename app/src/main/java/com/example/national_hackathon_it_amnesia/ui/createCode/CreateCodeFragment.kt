package com.example.national_hackathon_it_amnesia.ui.createCode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.R
import com.example.national_hackathon_it_amnesia.domain.sharedPreferences.SharedPreferences
import com.example.national_hackathon_it_amnesia.mvp.BaseFragment
import com.example.national_hackathon_it_amnesia.widgets.pincodeLayout.PinCodeActions
import kotlinx.android.synthetic.main.create_code_fragment.*
import javax.inject.Inject

class CreateCodeFragment: BaseFragment<CreateCodePresenterImpl>(), CreateCodeView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_code_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this

        toolbarCreateCode.setNavigationOnClickListener {
            onBackPressed()
        }
        pincodeLayout_first.setCallback(firstCallback)

        buttonNextCreateCode.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.EmptyCode), Toast.LENGTH_SHORT).show()
        }
    }


    private val firstCallback: PinCodeActions = object : PinCodeActions {

        override fun onPinEntered(pin: String) {
            buttonNextCreateCode.setOnClickListener {
                sharedPreferences.pinCode = pin
                findNavController().navigate(R.id.action_createCodeFragment_to_chatFragment)
            }
        }

        override fun onPinCleared() {
        }

        override fun onPinFilled() {
        }
    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createCreateCodeFragment()
            .inject(this)
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}