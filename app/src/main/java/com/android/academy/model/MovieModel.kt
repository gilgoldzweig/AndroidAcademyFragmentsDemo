package com.android.academy.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

data class MovieModel(
    var name: String,
    @DrawableRes val imageRes: Int,
    var overview: String = "",
    var releaseDate: String = "",
    var trailerURL: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeInt(imageRes)
        dest.writeString(overview)
        dest.writeString(releaseDate)
        dest.writeString(trailerURL)
    }

    override fun describeContents(): Int = 0

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<MovieModel> {
            override fun createFromParcel(parcel: Parcel): MovieModel = MovieModel(parcel)

            override fun newArray(size: Int): Array<MovieModel?> = arrayOfNulls(size)
        }
    }
}