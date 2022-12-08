package com.example.national_hackathon_it_amnesia.di.components

import com.example.national_hackathon_it_amnesia.di.modules.LogoModule
import com.example.national_hackathon_it_amnesia.di.scopes.LogoScope
import com.example.national_hackathon_it_amnesia.di.scopes.MainScope
import com.example.national_hackathon_it_amnesia.ui.logo.LogoFragment
import dagger.Subcomponent

@LogoScope
@Subcomponent(modules = [LogoModule::class])
interface LogoComponent {
    fun inject(fragment: LogoFragment)
}