package com.example.national_hackathon_it_amnesia.ui.registration

import com.example.national_hackathon_it_amnesia.mvp.BaseView

interface RegistrationView : BaseView {
    fun showError(message: String?)
    fun navToCreateCode()
}