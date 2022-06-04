package com.litreview.base.data.domain

import android.os.Parcel
import android.os.Parcelable

data class PublicUser(
    val id: String,
    val firstName: String,
    val secondName: String,
    val avatarUrl: String?,
    val description: String?,
    val rate: Double?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(firstName)
        parcel.writeString(secondName)
        parcel.writeString(avatarUrl)
        parcel.writeString(description)
        parcel.writeValue(rate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PublicUser> {
        override fun createFromParcel(parcel: Parcel): PublicUser {
            return PublicUser(parcel)
        }

        override fun newArray(size: Int): Array<PublicUser?> {
            return arrayOfNulls(size)
        }
    }
}