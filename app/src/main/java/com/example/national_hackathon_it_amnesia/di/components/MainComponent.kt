package com.example.national_hackathon_it_amnesia.di.components

import com.example.national_hackathon_it_amnesia.activity.MainActivity
import com.example.national_hackathon_it_amnesia.di.modules.MainModule
import com.example.national_hackathon_it_amnesia.di.scopes.MainScope
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}