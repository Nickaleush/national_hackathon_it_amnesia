package com.example.national_hackathon_it_amnesia.ui.registration

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.R
import com.example.national_hackathon_it_amnesia.domain.sharedPreferences.SharedPreferences
import com.example.national_hackathon_it_amnesia.mvp.BaseFragment
import com.example.national_hackathon_it_amnesia.widgets.countryPicker.CCPicker
import com.example.national_hackathon_it_amnesia.widgets.countryPicker.adapter.CountryPickerAdapter
import com.example.national_hackathon_it_amnesia.widgets.countryPicker.model.Country
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.registration_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment<RegistrationPresenterImpl>(), RegistrationView, DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var sheetView: View

    private lateinit var mBottomSheetDialog: BottomSheetDialog

    private lateinit var tvWrongCodeError: TextView

    private lateinit var tvRepeatSendCode: TextView

    private lateinit var tvResendCode: TextView

    private lateinit var etTextConfirmCode: EditText

    private lateinit var buttonSendConfirmAccountCode: Button

    var isKeyboardOpened = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        presenter.start()
        activity?.window?.statusBarColor = resources.getColor(R.color.white, null)
        setKeyboardListener()
        setOnClickListeners()
        countryCodeTextView.setOnClickListener {
            CCPicker.showPicker(
                requireActivity(),
                object : CountryPickerAdapter.OnCountrySelectedListener {
                    override fun onCountrySelected(country: Country?) {
                        val countryCode = country?.countryCode
                        countryCodeTextView.setText(countryCode)
                    }
                })
        }

        buttonNextRegistration.setOnClickListener {
//            val phoneNumber = phoneEditText.text.toString().replace(" ", "")
//            val finalPhoneNumber = phoneNumber.replace("-", "")
//            signUp((countryCodeTextView.text.toString() + finalPhoneNumber), SEND_DATE_FORMAT,
//                editTextName.text.toString(), editTextSurname.text.toString(),
//                editTextLastName.text.toString(),editTextPassword.text.toString())
            navToCreateCode()
        }

        toolbarRegistration.setNavigationOnClickListener { onBackPressed() }

    }

//    private fun signUp(phone: String, birthDay:String, name: String, surname:String, lastName:String, password: String) {
//        sharedPreferences.userName = name
//        val confirmPassword = editTextConfirmPassword.text.toString().trim()
//        val user = UserToSignUp(birthDay, phone,name, surname , lastName, password)
//
//        if (phone.isEmpty()) {
//            Toast.makeText(requireContext(), resources.getText(R.string.EnterName), Toast.LENGTH_LONG).show()
//        }
//
//        if (name.isEmpty()) {
//            Toast.makeText(requireContext(), resources.getText(R.string.EnterName), Toast.LENGTH_LONG).show()
//        }
//
//        if (surname.isEmpty()) {
//            Toast.makeText(requireContext(), resources.getText(R.string.EnterSurname), Toast.LENGTH_LONG).show()
//        }
//
//        if (lastName.isEmpty()) {
//            Toast.makeText(requireContext(), resources.getText(R.string.EnterLastName), Toast.LENGTH_LONG).show()
//        }
//
//        if (password.isEmpty()) {
//            Toast.makeText(requireContext(), resources.getText(R.string.EnterPassword), Toast.LENGTH_LONG).show()
//        }
//
//        if (confirmPassword.isEmpty()) {
//            Toast.makeText(requireContext(), resources.getText(R.string.ConfirmPasswordEmpty), Toast.LENGTH_LONG).show()
//        }
//
//        if (password == confirmPassword &&  phone.isNotEmpty() && name.isNotEmpty()
//            && surname.isNotEmpty()  && lastName.isNotEmpty()  && password.isNotEmpty()) {
//             if (sharedPreferences.adminMode) presenter.signUpAdmin(user)
//            else presenter.signUpClient(user)
//        } else {
//            Toast.makeText(requireContext(), resources.getText(R.string.CheckCredentials), Toast.LENGTH_LONG).show()
//        }
//    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createRegistrationFragment()
            .inject(this)
    }

//    override fun showConfirmationDialog() {
//        sheetView = requireActivity().layoutInflater.inflate(R.layout.confirmation_create_account, null)
//        mBottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
//        dialogOpened = true
//        mBottomSheetDialog.setContentView(sheetView)
//        mBottomSheetDialog.setCancelable(false)
//        mBottomSheetDialog.show()
//        val mBehavior = BottomSheetBehavior.from(sheetView.parent as View)
//        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        tvWrongCodeError = sheetView.findViewById(R.id.tv_wrongAccountCodeError)
//        etTextConfirmCode = sheetView.findViewById(R.id.editTextConfirmAccountCode)
//        tvRepeatSendCode = sheetView.findViewById(R.id.textViewRepeatSendAccountCode)
//        tvResendCode = sheetView.findViewById(R.id.tv_resendAccountCode)
//        buttonSendConfirmAccountCode = sheetView.findViewById(R.id.buttonSendConfirmAccountCode)
//        setupTimer()
//        buttonSendConfirmAccountCode.setOnClickListener {
//            if (sharedPreferences.adminMode) presenter.confirmAdminAccount(Code(confirmCodeAccount))
//            else presenter.confirmClientAccount(Code(confirmCodeAccount))
//        }
//
//        RxTextView.textChanges(etTextConfirmCode)
//            .debounce(300, TimeUnit.MILLISECONDS)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                confirmCodeAccount = it.toString()
//                if (tvWrongCodeError.visibility == View.VISIBLE) {
//                    tvWrongCodeError.visibility = View.GONE
//                    tvRepeatSendCode.visibility = View.VISIBLE
//                    etTextConfirmCode.setBackgroundResource(R.drawable.bottom_line_edit_text)
//                }
//            }, Throwable::printStackTrace)
//    }

    override fun navToCreateCode() {
        if (dialogOpened) {
            mBottomSheetDialog.dismiss()
        }
//        cancelTimer()
        Toast.makeText(
            requireContext(),
            getString(R.string.RegistrationSuccess),
            Toast.LENGTH_SHORT
        ).show()
        findNavController().navigate(R.id.action_registrationFragment_to_createCodeFragment)
    }

//    private fun setupTimer() {
//        if (remainSeconds > 0) {
//            startTime(remainSeconds)
//        } else if (remainSeconds == 0) {
//            showResendAction()
//        }
//    }

//    private fun startTime(time: Int) {
//        timerConfirmAccount?.cancel()
//        timerConfirmAccount = Timer()
//        timerConfirmAccount?.schedule(ResendCodeTask(time), 0, 1000)
//        tvWrongCodeError.visibility = View.GONE
//        tvRepeatSendCode.visibility = View.VISIBLE
//        tvResendCode.visibility = View.GONE
//    }
//
//    inner class ResendCodeTask(private var timeOutSec: Int) : TimerTask() {
//        override fun run() {
//            if (timeOutSec <= 0) {
//                showResendAction()
//                cancelTimer()
//                cancel()
//                return
//            }
//            updateTimeText(timeOutSec)
//            timeOutSec--
//        }
//    }
//
//    private fun updateTimeText(time: Int) {
//        requireActivity().runOnUiThread {
//            tvRepeatSendCode.text = String.format(
//                "%s %d:%02d",
//                getString(R.string.resend_two_min),
//                time / 60,
//                time % 60
//            )
//        }
//    }
//
//    private fun cancelTimer() {
//        remainSeconds = 0
//        timerConfirmAccount?.cancel()
//    }
//
//    private fun showResendAction() {
//        requireActivity().runOnUiThread {
//            mBottomSheetDialog.setCancelable(true)
//            etTextConfirmCode.visibility = View.VISIBLE
//            tvWrongCodeError.visibility = View.GONE
//            tvRepeatSendCode.visibility = View.GONE
//            tvResendCode.visibility = View.VISIBLE
//        }
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        cancelTimer()
//        if (dialogOpened) mBottomSheetDialog.dismiss()
//    }

    private fun setOnClickListeners() {
        birthDayTextView.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        buttonNextRegistration.setOnClickListener {
//            presenter.savePetShortForm(sendShortPetInfo())
        }

        editTextSurname.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextLastName.callOnClick()
            }
        }

        editTextSurname.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextName.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextName.callOnClick()
            }
        }
        editTextName.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextLastName.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextLastName.callOnClick()
            }
        }

        editTextLastName.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextPassword.callOnClick()
            }
        }

        editTextPassword.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextConfirmPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextLastName.callOnClick()
            }
        }

        editTextConfirmPassword.setOnClickListener {
            isKeyboardOpened = true
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month+1
        savedYear = year
        getDateTimeCalendar()
        resultMonth = if (savedMonth <10) "0$savedMonth" else "$savedMonth"
        resultDay = if(savedDay <10) "0$savedDay" else "$savedDay"
        dateTime = "$savedYear-$savedMonth-$savedDay"
        val outputFormat= SimpleDateFormat("dd MMMM yyyy")
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val date= inputFormat.parse(dateTime)
        SEND_DATE_FORMAT = inputFormat.format(date)
        val formattedDate = outputFormat.format(date)
        birthDayTextView.text = formattedDate
    }

    private fun setKeyboardListener() {
        val activityRootView: View = requireActivity().window.decorView.findViewById(android.R.id.content)
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = activityRootView.rootView.height - activityRootView.height
            if (heightDiff > 100) {
                if (isKeyboardOpened) {
                    with(scroll_view_registration) {
                        isKeyboardOpened = false
                        smoothScrollBy(0, buttonNextRegistration.y.toInt())
                    }
                }
            }
        }
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), getString(R.string.CheckCredentials), Toast.LENGTH_SHORT).show()

    companion object {
        private var remainSeconds = 120
        private var timerConfirmAccount: Timer? = null
        var confirmCodeAccount = ""
        var dialogOpened = false
        var year = 0
        var month = 0
        var day = 0
        var savedYear = 0
        var savedMonth = 0
        var savedDay = 0
        var resultMonth = ""
        var resultDay = ""
        var dateTime = ""
        var SEND_DATE_FORMAT = ""
    }
}