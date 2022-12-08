package com.example.national_hackathon_it_amnesia.ui.profile

import com.example.national_hackathon_it_amnesia.mvp.BaseView

interface ProfileView : BaseView {
    fun showError(message: String?)
    //fun initUserInfo(user: UserDTO)
}