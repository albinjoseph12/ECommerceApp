package com.eshopthis.finds.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.eshopthis.finds.data.Converters
import com.google.gson.annotations.SerializedName
import java.util.Date

@Entity(tableName = "user")
@TypeConverters(Converters::class)
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    var username: String = "",
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var gender: String = "",
    var ageGroup: String = "",
    var contactNumber: String = "",
    var activeToken: String = "",
    var passiveToken: String = "",
    var isActive: Boolean = false,
    var referralEmail: String? = null,
    @SerializedName("created_date")
    var createdDate: Date? = null,
    @SerializedName("last_login_date")
    var lastLoginDate: Date? = null,
    var address1: String = "",
    var address2: String = "",
    var city: String = "",
    var pincode: String = "",
    var state: String = "",
    var country: String = "",
    var billingAddress1: String = ""
)