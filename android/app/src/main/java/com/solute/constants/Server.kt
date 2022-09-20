package com.solute.constants

object Server {
          val fcmServer = "https://fcm.googleapis.com/fcm/send"

          val productionHost = "https://friendly-r227wnciga-el.a.run.app"
          val devHost = "http://192.168.0.170"
          val socketPort = "8080"
          val authenticate = "authentication/authenticate"
          val login = "authentication/login"
          val searchUsers = "search/users"

          val getProfile = "Search/getProfile"
          val downloadProfiles = "Search/download_profiles"
          val getConversation = "conversation/getAll"
          val createConversation = "conversation/create"
          val updateLastSeen = "User/update_last_seen"

          val getMessage = "message/getAll"
          val sendMessage = "message/send"
          val updateProfilePic = "profile/updatePicture"
          val updateFcm = "user/update_fcm"
          val updateProfilePicToConv = "User/update_profile_in_conv"
          val updateMyProfile = "user/update_my_profile"
          val sendFriendRequest = "friends/send_friend_request"
          val acceptFriendRequest = "friends/accept_friend_request"
          val unFriend = "friends/unfriend"
          val rejectFriendRequest = "friends/decline_friend_request"
          val getSentRequest = "friends/sent_reqest"
          val allFriendRequest = "friends/all_friend_request"
          val getFriendList = "friends/all_friends"
          val getAllUsers = "search/users"
          val reportUser = "user/report"
          val blockUser = "user/block"
          val uploadContacts = "invite/updateContacts"
          val retriveContacts = "invite/retriveContacts"
          val searchCompanyName = "company/findCompanyByNamme"
          val searchDesignationName = "designation/findDesignationByNamme"
          const val searchTechnologyName = "technology/findTechnologyByNamme"


//      fun  getImageUrl(path:String):String{
//            return host + path
//      }

}
