package com.example.national_hackathon_it_amnesia.ui.auth

import com.example.national_hackathon_it_amnesia.di.scopes.AuthScope
import com.example.national_hackathon_it_amnesia.mvp.BasePresenterImpl
import javax.inject.Inject

@AuthScope
class AuthPresenterImpl  @Inject constructor() : BasePresenterImpl<AuthView>(), AuthPresenter {

    override fun start() = Unit

    override lateinit var view: AuthView

}