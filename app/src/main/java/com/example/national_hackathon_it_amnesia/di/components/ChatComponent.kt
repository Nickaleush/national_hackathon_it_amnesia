package com.example.national_hackathon_it_amnesia.di.components

import com.example.national_hackathon_it_amnesia.di.modules.ChatModule
import com.example.national_hackathon_it_amnesia.di.scopes.ChatScope
import com.example.national_hackathon_it_amnesia.ui.chat.ChatFragment
import dagger.Subcomponent

@ChatScope
@Subcomponent(modules = [ChatModule::class])
interface ChatComponent {
    fun inject(fragment: ChatFragment)
}