package com.solute.constants


class Constants {

    companion object {
         val NOTIFICATION_BROADCAST_RECEIVER_MESSAGE_RECEIVED =
            "com.mycompany.myapp.NOTIFICATION_RECEIVED"
        val mobileAdsKey = "ca-app-pub-7801623475724735~4936827022" //Live

         val mobileAdsRewardLiveKey = "ca-app-pub-7801623475724735/1661402629" //Live
          val mobileAdsRewardTestKey = "ca-app-pub-3940256099942544/1712485313" //Test

          val mobileAdsBannerLiveKey = "ca-app-pub-7801623475724735/4802941563" //Live
          val mobileAdsBannerTestKey = "ca-app-pub-3940256099942544/2934735716" //Test

          val mobileAdsInterstitialLiveKey = "ca-app-pub-7801623475724735/9372371689" //Live
          val mobileAdsInterstitialTestKey = "ca-app-pub-7801623475724735~4936827022" //Test

          val mobileAdsNativeLiveKey = "ca-app-pub-7801623475724735/9432081434" //Live
          val mobileAdsNativeTestKey = "cca-app-pub-3940256099942544/3986624511" //Test
        val defaultErrorMessage = "Something went wrong, please try after sometime!"
        
        val emptyConversationMessage = "You haven't started any conversation yet.Please click on Plus icon below and meeting new people to start chatting!"
    }


    class MessageFields {
         val name = "name"
         val text = "text"
         val photoURL = "photoURL"
         val imageURL = "imageURL"
    }


}
