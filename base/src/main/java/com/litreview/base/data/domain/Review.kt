package com.litreview.base.data.domain

import android.os.Parcel
import android.os.Parcelable

data class Review(
    val id: String,
    val date: String,
    val text: String,
    val rate: Double,
    val book: Book,
    val userInfo: PublicUser? = null
) : Parcelable {
    val userName: String
        get() {
            return userInfo?.firstName + if (userInfo?.firstName.isNullOrEmpty()) " " else "" +
                    userInfo?.secondName
        }

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readParcelable(Book::class.java.classLoader)!!,
        parcel.readParcelable(PublicUser::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(date)
        parcel.writeString(text)
        parcel.writeDouble(rate)
        parcel.writeParcelable(book, flags)
        parcel.writeParcelable(userInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }
    }

}