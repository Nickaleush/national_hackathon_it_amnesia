package com.example.national_hackathon_it_amnesia.di.modules

import com.example.national_hackathon_it_amnesia.di.scopes.ChatScope
import com.example.national_hackathon_it_amnesia.ui.chat.ChatPresenter
import com.example.national_hackathon_it_amnesia.ui.chat.ChatPresenterImpl
import dagger.Module

@Module
interface ChatModule {
    @ChatScope
    fun presenter(presenter: ChatPresenterImpl): ChatPresenter
}