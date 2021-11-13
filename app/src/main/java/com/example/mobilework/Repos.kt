package com.example.mobilework

import com.google.gson.annotations.SerializedName

data class Repos(
    @SerializedName("name") val name_rep: String?,
    @SerializedName("updated_at") val last_update_rep: String?)