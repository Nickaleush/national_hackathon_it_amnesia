package com.example.national_hackathon_it_amnesia.domain.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NoneAuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}