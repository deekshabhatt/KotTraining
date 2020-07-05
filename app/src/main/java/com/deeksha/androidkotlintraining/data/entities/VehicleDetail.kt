package com.deeksha.androidkotlintraining.data.entities

import android.os.Parcel
import android.os.Parcelable

data class VehicleDetail(
    val name: String?,
    val make: String?,
    val color: String?,
    val series: String?,
    val fuel_type: String?,
    val fuel_level: String?,
    val transmission: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(make)
        parcel.writeString(color)
        parcel.writeString(series)
        parcel.writeString(fuel_type)
        parcel.writeString(fuel_level)
        parcel.writeString(transmission)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VehicleDetail> {
        override fun createFromParcel(parcel: Parcel): VehicleDetail {
            return VehicleDetail(parcel)
        }

        override fun newArray(size: Int): Array<VehicleDetail?> {
            return arrayOfNulls(size)
        }
    }

}