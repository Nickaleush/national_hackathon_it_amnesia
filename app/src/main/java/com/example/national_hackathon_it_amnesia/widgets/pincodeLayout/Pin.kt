package com.example.national_hackathon_it_amnesia.widgets.pincodeLayout

import android.content.Context
import android.view.View
import android.widget.ViewSwitcher
import com.example.national_hackathon_it_amnesia.R

class Pin(context: Context?) : ViewSwitcher(context){

	init {
		View.inflate(context, R.layout.pin, this)
	}
}
