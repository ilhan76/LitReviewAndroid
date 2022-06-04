package com.litreview.base.data.domain

import android.os.Parcel
import android.os.Parcelable

data class Book(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String,
    val rate: Double,
    val author: Author?
) : Parcelable {

    val authorName: String
        get() {
            return author?.firstName + if (author?.firstName.isNullOrEmpty()) " " else "" +
                    author?.lastName + if (author?.lastName.isNullOrEmpty()) " " else "" +
                    author?.middleName
        }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readParcelable(Author::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeDouble(rate)
        parcel.writeParcelable(author, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}
