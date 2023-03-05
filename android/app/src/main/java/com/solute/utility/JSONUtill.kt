package com.solute.utility

import com.friendly.framework.dataclass.FriendlyProfile

class JSONUtill
{
    fun getUserIdsForRequest(profiles:ArrayList<FriendlyProfile>):String{
        var requestMobiles = "["
        var count = 0
        for (item in profiles)
        {
            requestMobiles = requestMobiles + "\"${item.Id}\""
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