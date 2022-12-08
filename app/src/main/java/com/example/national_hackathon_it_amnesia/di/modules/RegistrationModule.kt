package com.example.national_hackathon_it_amnesia.di.modules

import com.example.national_hackathon_it_amnesia.di.scopes.RegistrationScope
import com.example.national_hackathon_it_amnesia.ui.registration.RegistrationPresenter
import com.example.national_hackathon_it_amnesia.ui.registration.RegistrationPresenterImpl
import dagger.Module

@Module
interface RegistrationModule {
    @RegistrationScope
    fun presenter(presenter: RegistrationPresenterImpl): RegistrationPresenter
}