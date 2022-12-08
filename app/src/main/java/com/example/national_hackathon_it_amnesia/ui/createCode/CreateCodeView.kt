package com.example.national_hackathon_it_amnesia.ui.createCode

import com.example.national_hackathon_it_amnesia.mvp.BaseView

interface CreateCodeView : BaseView {
    fun showError(message: String?)
}