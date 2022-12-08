package com.example.national_hackathon_it_amnesia.ui.profile

import android.annotation.SuppressLint
import com.example.national_hackathon_it_amnesia.domain.sharedPreferences.SharedPreferences
import com.example.national_hackathon_it_amnesia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenterImpl @Inject constructor() : BasePresenterImpl<ProfileView>(),
    ProfilePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ProfileView

    override fun start() = Unit

}