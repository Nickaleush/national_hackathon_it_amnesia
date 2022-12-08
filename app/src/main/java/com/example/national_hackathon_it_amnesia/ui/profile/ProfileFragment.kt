package com.example.national_hackathon_it_amnesia.ui.profile

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
import com.example.national_hackathon_it_amnesia.utils.navigateTo
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class ProfileFragment: BaseFragment<ProfilePresenterImpl>(), ProfileView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createProfileFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        buttonClientLogOut.setOnClickListener {
            sharedPreferences.accessToken = null
            sharedPreferences.pinCode = null
            findNavController().navigateTo(findNavController(),R.id.action_profileFragment_to_registrationFragment, true)
        }
    }

    override fun onBackPressed() {
      requireActivity().onBackPressedDispatcher.onBackPressed()
    }


    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

//    override fun initUserInfo(user: UserDTO) {
//        if (this.isVisible) {
//            clientCredentialsTextView.text = user.credentials
//            (user.firstName + " " + user.middleName + " " + user.lastName).also {
//                clientFullNameTextView.text = it
//            }
//            clientBirthDayTextView.text = user.birthDate
//        }
//    }
}