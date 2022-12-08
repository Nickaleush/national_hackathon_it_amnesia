package com.example.national_hackathon_it_amnesia.di.modules

import com.example.national_hackathon_it_amnesia.di.scopes.LogoScope
import com.example.national_hackathon_it_amnesia.di.scopes.MainScope
import com.example.national_hackathon_it_amnesia.ui.logo.LogoPresenter
import com.example.national_hackathon_it_amnesia.ui.logo.LogoPresenterImpl
import dagger.Module

@Module
interface LogoModule {
    @LogoScope
    fun presenter(presenter: LogoPresenterImpl): LogoPresenter
}