package com.friendly.framework.constants

object Server {
    var allServers = arrayOf(
        "http://192.168.1.8:1010",
        "http://128.168.178.68.host.secureserver.net:8080",
        "https://solute-rest-r227wnciga-el.a.run.app",
    )

    fun getRestApiEndPoint():String{
        return allServers[2]+"/api/"
    }

    fun getRestApiEndPointFor(api:String):String{
        return allServers[2]+"/api/$api"
    }

}
