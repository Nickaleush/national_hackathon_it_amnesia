package com.example.national_hackathon_it_amnesia.di.modules

import com.example.national_hackathon_it_amnesia.di.scopes.CreateCodeScope
import com.example.national_hackathon_it_amnesia.ui.createCode.CreateCodePresenter
import com.example.national_hackathon_it_amnesia.ui.createCode.CreateCodePresenterImpl

import dagger.Module

@Module
interface CreateCodeModule {
    @CreateCodeScope
    fun presenter(presenter: CreateCodePresenterImpl): CreateCodePresenter
}