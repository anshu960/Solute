package com.solute.app

import com.friendly.framework.dataclass.FriendlyUser

class Membership {
    var whitelistedUsers = arrayListOf("8904242221","9431208041")
    var user = FriendlyUser()
    fun isMembershipActive(completion: (Boolean) -> Unit){
        var isWhiteliste = false
        whitelistedUsers.forEach {
            if(user.mobile.contains(it)){
                isWhiteliste = true
            }
        }
        completion(isWhiteliste)
    }
}