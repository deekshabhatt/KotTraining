package com.deeksha.androidkotlintraining.data.entities

import android.os.Parcel
import android.os.Parcelable

data class Cab(
    val id: String?,
    val modelIdentifier: String?,
    val modelName: String?,
    val group: String?,
    val licensePlate: String?,
    val carImageUrl: String?,
    val vehicleDetails: VehicleDetail?,
    val location: Location?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(VehicleDetail::class.java.classLoader),
        parcel.readParcelable(Location::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(modelIdentifier)
        parcel.writeString(modelName)
        parcel.writeString(group)
        parcel.writeString(licensePlate)
        parcel.writeString(carImageUrl)
        parcel.writeParcelable(vehicleDetails, flags)
        parcel.writeParcelable(location, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cab> {
        override fun createFromParcel(parcel: Parcel): Cab {
            return Cab(parcel)
        }

        override fun newArray(size: Int): Array<Cab?> {
            return arrayOfNulls(size)
        }
    }
}