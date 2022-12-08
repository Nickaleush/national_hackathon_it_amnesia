package com.example.national_hackathon_it_amnesia.di.components

import com.example.national_hackathon_it_amnesia.di.modules.RegistrationModule
import com.example.national_hackathon_it_amnesia.di.scopes.RegistrationScope
import com.example.national_hackathon_it_amnesia.ui.registration.RegistrationFragment
import dagger.Subcomponent

@RegistrationScope
@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {
    fun inject(fragment: RegistrationFragment)
}