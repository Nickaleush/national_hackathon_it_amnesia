package com.example.national_hackathon_it_amnesia.widgets.pincodeLayout

interface PinCodeActions {
	fun onPinEntered(pin: String)
	fun onPinCleared()
	fun onPinFilled()
}