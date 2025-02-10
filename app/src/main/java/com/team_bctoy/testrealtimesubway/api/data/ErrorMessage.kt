package com.team_bctoy.testrealtimesubway.api.data

import com.google.gson.annotations.SerializedName

data class ErrorMessage(
    @SerializedName("statue") val statue: Int,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("link") val link: String,
    @SerializedName("developerMessage") val developerMessage: String,
    @SerializedName("total") val total: Int
)
