package com.example.national_hackathon_it_amnesia.di

import android.content.Context
import com.example.national_hackathon_it_amnesia.App
import com.example.national_hackathon_it_amnesia.di.components.*
import com.example.national_hackathon_it_amnesia.di.modules.LocalDataModule
import com.example.national_hackathon_it_amnesia.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(  modules = [
    AppModule::class,
    LocalDataModule::class,
    NetworkModule::class,
])

interface AppComponent {

    fun inject(application: App)

    fun context(): Context

    fun createMainActivity(): MainComponent

    fun createLogoFragment(): LogoComponent

    fun createChatFragment(): ChatComponent

    fun createAuthFragment(): AuthComponent

    fun createRegistrationFragment(): RegistrationComponent

    fun createCreateCodeFragment(): CreateCodeComponent

    fun createProfileFragment(): ProfileComponent
}