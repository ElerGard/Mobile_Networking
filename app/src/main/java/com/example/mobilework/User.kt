package com.example.mobilework

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url") val avatar_url: String?,
    @SerializedName("login") val login: String?)

