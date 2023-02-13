package com.solute.utility.location

import com.google.android.gms.location.LocationResult

interface locationListener {
    fun locationResponse(locationResult: LocationResult)
}
