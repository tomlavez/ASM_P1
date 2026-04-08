package cat.deim.asm_18.pedalean2.domain.models;

import java.util.Date

data class BikeRent(
    val uuid: String,
    val name: String
)

data class UserRent(
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String
)

data class Rent(
    val uuid: String,
    val timeStart: Date,
    val timeEnd: Date,
    val isRented: Boolean,
    val rentTime: Int,
    val rentMeters: Int,
    val rentStartLatitude: Float,
    val rentStartLongitude: Float,
    val bikeRent: BikeRent,
    val rentedBy: UserRent
)