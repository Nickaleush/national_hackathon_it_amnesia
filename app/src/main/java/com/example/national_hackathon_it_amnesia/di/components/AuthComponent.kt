package com.example.national_hackathon_it_amnesia.di.components

import com.example.national_hackathon_it_amnesia.di.modules.AuthModule
import com.example.national_hackathon_it_amnesia.di.scopes.AuthScope
import com.example.national_hackathon_it_amnesia.ui.auth.AuthFragment
import dagger.Subcomponent

@AuthScope
@Subcomponent(modules = [AuthModule::class])
interface AuthComponent {
    fun inject(fragment: AuthFragment)
}