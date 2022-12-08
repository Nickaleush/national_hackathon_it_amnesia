package com.example.national_hackathon_it_amnesia.ui.logo

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.mvp.BaseFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.logo_fragment.*
import com.example.national_hackathon_it_amnesia.R
import com.example.national_hackathon_it_amnesia.domain.sharedPreferences.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogoFragment: BaseFragment<LogoPresenterImpl>(), LogoView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.logo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = resources.getColor(R.color.black, null)
        pulsator.start()
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch  {
            delay(500)
//            if (sharedPreferences.pinCode != null && sharedPreferences.userName != null)
                findNavController().navigate(R.id.action_logoFragment_to_authFragment)
//            else findNavController().navigate(R.id.action_logoFragment_to_welcomeFragment)
        }.start()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createLogoFragment()
            .inject(this)
    }

    override fun onBackPressed() {
        requireActivity().finish()
    }

}