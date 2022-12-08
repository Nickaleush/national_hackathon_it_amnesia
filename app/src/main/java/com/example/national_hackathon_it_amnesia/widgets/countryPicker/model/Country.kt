package com.example.national_hackathon_it_amnesia.widgets.countryPicker.model

import java.util.*

class Country : Comparator<Country?> {
    var countryName: String? = null
    var countryCode: String? = null
    var countryShortCode: String? = null

    override fun compare(o1: Country?, o2: Country?): Int {
        return o1?.countryName!!.compareTo(o2?.countryName!!, ignoreCase = true)
    }
}
