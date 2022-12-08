package com.example.national_hackathon_it_amnesia.ui.registration

import android.annotation.SuppressLint
import com.example.national_hackathon_it_amnesia.domain.sharedPreferences.SharedPreferences
import com.example.national_hackathon_it_amnesia.mvp.BasePresenterImpl
import javax.inject.Inject

class RegistrationPresenterImpl @Inject constructor() : BasePresenterImpl<RegistrationView>(),
    RegistrationPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: RegistrationView

//    @SuppressLint("CheckResult")
//    override fun signUpClient(userToSignUp: UserToSignUp) {
//        authorizationApi.signUpClient(userToSignUp)
//            .subscribeOn(Schedulers.io())
//            .observeOn(
//                AndroidSchedulers.mainThread()
//            )
//            .subscribe({
//                sharedPreferences.accessToken = it.accessToken
//                view.showConfirmationDialog()
//            }, {
//                view.showError(it.message)
//            })
//    }
//
//
//    @SuppressLint("CheckResult")
//    override fun confirmClientAccount(code: Code) {
//        mainApi.confirmClientAccount(code)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                sharedPreferences.accessToken = it.accessToken
//                view.navToCreateCode()
//                view.setupPushToken()
//            }, {
//                view.showError(it.message)
//            })
//    }

    override fun start() = Unit

}