package com.example.national_hackathon_it_amnesia.ui.logo

import com.example.national_hackathon_it_amnesia.di.scopes.LogoScope
import com.example.national_hackathon_it_amnesia.di.scopes.MainScope
import com.example.national_hackathon_it_amnesia.mvp.BasePresenterImpl
import javax.inject.Inject

@LogoScope
class LogoPresenterImpl  @Inject constructor() : BasePresenterImpl<LogoView>(), LogoPresenter {
    override fun start() = Unit
    override lateinit var view: LogoView
}