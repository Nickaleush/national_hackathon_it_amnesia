package com.example.national_hackathon_it_amnesia.ui.chat

import com.example.national_hackathon_it_amnesia.di.scopes.ChatScope
import com.example.national_hackathon_it_amnesia.mvp.BasePresenterImpl
import com.example.national_hackathon_it_amnesia.ui.logo.LogoPresenter
import com.example.national_hackathon_it_amnesia.ui.logo.LogoView
import javax.inject.Inject

@ChatScope
class ChatPresenterImpl  @Inject constructor() : BasePresenterImpl<LogoView>(), LogoPresenter {

    override fun start() = Unit

    override lateinit var view: LogoView

}