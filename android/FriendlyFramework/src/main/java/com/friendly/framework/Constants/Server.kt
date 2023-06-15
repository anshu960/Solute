package com.friendly.framework.constants

object Server {
    var allServers = arrayOf(
        "https://8bf0-171-79-22-27.ngrok-free.app",
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
