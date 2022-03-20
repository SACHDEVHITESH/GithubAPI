package com.example.githubapi.data.remote.api.models

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class ClosedPRModel(
    @SerializedName("user") var user: GithubUserModel,
    @SerializedName("created_at") var createdAt: Date,
    @SerializedName("closed_at") var closedAt: Date,
    @SerializedName("title") var title: String
): Serializable,
    Parcelable {
    @SuppressLint("SimpleDateFormat")
    fun getCreatedDateAsString(): String? {
        val formatter = SimpleDateFormat("dd MMMM yyyy, hh:mm:ss")
        return formatter.format(createdAt)
    }

    @SuppressLint("SimpleDateFormat")
    fun getClosedDateAsString(): String? {
        val formatter = SimpleDateFormat("dd MMMM yyyy, hh:mm:ss")
        return formatter.format(closedAt)
    }
}