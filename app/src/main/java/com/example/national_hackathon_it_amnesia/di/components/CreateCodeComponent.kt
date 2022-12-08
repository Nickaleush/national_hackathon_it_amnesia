package com.example.national_hackathon_it_amnesia.di.components

import com.example.national_hackathon_it_amnesia.di.modules.CreateCodeModule
import com.example.national_hackathon_it_amnesia.di.scopes.CreateCodeScope
import com.example.national_hackathon_it_amnesia.ui.createCode.CreateCodeFragment
import dagger.Subcomponent

@CreateCodeScope
@Subcomponent(modules = [CreateCodeModule::class])
interface CreateCodeComponent {
    fun inject(fragment: CreateCodeFragment)
}
