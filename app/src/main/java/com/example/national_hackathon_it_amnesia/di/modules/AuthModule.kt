package com.example.national_hackathon_it_amnesia.di.modules

import com.example.national_hackathon_it_amnesia.di.scopes.AuthScope
import com.example.national_hackathon_it_amnesia.ui.auth.AuthPresenter
import com.example.national_hackathon_it_amnesia.ui.auth.AuthPresenterImpl
import dagger.Module

@Module
interface AuthModule {
    @AuthScope
    fun presenter(presenter: AuthPresenterImpl): AuthPresenter
}