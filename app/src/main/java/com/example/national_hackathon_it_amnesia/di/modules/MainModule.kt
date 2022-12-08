package com.example.national_hackathon_it_amnesia.di.modules

import com.example.national_hackathon_it_amnesia.ui.activity.MainPresenter
import com.example.national_hackathon_it_amnesia.ui.activity.MainPresenterImpl
import com.example.national_hackathon_it_amnesia.di.scopes.MainScope
import dagger.Module

@Module
interface MainModule {
    @MainScope
    fun presenter(presenter: MainPresenterImpl): MainPresenter
}