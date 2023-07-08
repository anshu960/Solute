package com.friendly.framework.network

import com.google.gson.annotations.SerializedName

class RestApiResponse<ResponseBody> constructor() : ApiResponse() {

    private constructor(data: ResponseBody) : this() {
        this.payload = data.toString()
    }
    companion object {
        const val PAYLOAD = "Payload"

        @JvmStatic
        fun <ResponseBody> create(data: ResponseBody): RestApiResponse<ResponseBody> {
            return RestApiResponse(data)
        }
    }
}