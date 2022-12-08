package com.example.national_hackathon_it_amnesia.ui.activity

import com.example.national_hackathon_it_amnesia.mvp.BasePresenterImpl
import com.example.national_hackathon_it_amnesia.di.scopes.MainScope
import javax.inject.Inject

@MainScope
class MainPresenterImpl  @Inject constructor() : BasePresenterImpl<MainView>(), MainPresenter {

    override fun start() = Unit
    override lateinit var view: MainView

}