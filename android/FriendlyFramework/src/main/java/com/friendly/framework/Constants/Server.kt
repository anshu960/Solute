package com.friendly.framework.constants

object Server {
    var allServers = arrayOf(
        "http://128.168.178.68.host.secureserver.net:8080",
        "http://128.168.178.68.host.secureserver.net:8080",
        "https://solute-rest-r227wnciga-el.a.run.app",
    )

    fun getRestApiEndPoint():String{
        return allServers[0]+"/api/"
    }

    fun getRestApiEndPointFor(api:String):String{
        return allServers[0]+"/api/$api"
    }

}
