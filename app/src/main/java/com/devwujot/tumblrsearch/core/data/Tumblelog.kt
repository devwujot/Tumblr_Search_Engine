package com.devwujot.tumblrsearch.core.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tumblelog(
    val title: String?,
    val name: String?,
    val url: String?,
    val timezone: String?,
    @SerializedName("avatar_url_128")
    val avatarUrl: String?
) : Parcelable