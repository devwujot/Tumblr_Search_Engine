package com.devwujot.tumblrsearch.core.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: Long?,
    val url: String?,
    val type: String?,
    val date: String?,
    @SerializedName("note-count")
    val noteCount: String?,
    @SerializedName("photo-url-500")
    val photoUrl: String?,
    @SerializedName("reblogged-root-name")
    val ownerName: String?,
    @SerializedName("reblogged-root-title")
    val ownerTitle: String?,
    @SerializedName("reblogged_root_avatar_url_64")
    val ownerAvatar: String?,
    val tumblelog: Tumblelog?
) : Parcelable