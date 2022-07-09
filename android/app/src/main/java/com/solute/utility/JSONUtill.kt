package com.friendly.utility

import com.solute.dataclass.Profile


class JSONUtill
{
    fun getUserIdsForRequest(profiles:ArrayList<Profile>):String{
        var requestMobiles = "["
        var count = 0
        for (item in profiles)
        {
            requestMobiles = requestMobiles + "\"${item._id}\""
            if (count < profiles.count() - 1)
            {
                requestMobiles = requestMobiles + ","
            } else
            {
                requestMobiles = requestMobiles  + "]"
            }
            count = count + 1
        }
        return requestMobiles
    }
}