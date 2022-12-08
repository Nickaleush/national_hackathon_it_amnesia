package com.example.national_hackathon_it_amnesia.ui.createCode

import com.example.national_hackathon_it_amnesia.domain.sharedPreferences.SharedPreferences
import com.example.national_hackathon_it_amnesia.mvp.BasePresenterImpl
import javax.inject.Inject

class CreateCodePresenterImpl @Inject constructor() : BasePresenterImpl<CreateCodeView>(),
    CreateCodePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: CreateCodeView

    override fun start() = Unit

}