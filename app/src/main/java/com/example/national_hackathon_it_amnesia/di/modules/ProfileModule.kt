package com.example.national_hackathon_it_amnesia.di.modules

import com.example.national_hackathon_it_amnesia.di.scopes.ProfileScope
import com.example.national_hackathon_it_amnesia.ui.profile.ProfilePresenter
import com.example.national_hackathon_it_amnesia.ui.profile.ProfilePresenterImpl
import dagger.Module

@Module
interface ProfileModule {
    @ProfileScope
    fun presenter(presenter: ProfilePresenterImpl): ProfilePresenter
}