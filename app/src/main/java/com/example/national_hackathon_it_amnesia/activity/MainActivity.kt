package com.example.national_hackathon_it_amnesia.activity

import android.os.Bundle
import com.example.national_hackathon_it_amnesia.R
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.mvp.BaseActivity
import com.example.national_hackathon_it_amnesia.mvp.BaseFragment

class MainActivity : BaseActivity<MainPresenterImpl>(), MainView {

    interface OnBackPressedListener {
        fun onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.view = this
    }

    override fun onBackPressed() {
        if (BaseFragment.backPressedListener!=null) {
            BaseFragment.backPressedListener!!.onBackPressed()
        } else  {
            super.onBackPressed()
        }
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createMainActivity()
            .inject(this)
    }
}