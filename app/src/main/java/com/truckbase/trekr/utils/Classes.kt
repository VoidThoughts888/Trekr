package com.truckbase.trekr.utils

fun main() {
    var lexus = Cars("Lexus", 2023, "Blue", "Automatic", "350")
    lexus.carsMethod(200)

    var tvs = Tricycles("handle", 4, 2020, "TVS", "King")
}

open class Vehicles(var tyres: Int, var brand: String, var year: Int, var model: String)

class Cars(
    var carBrand: String,
    var carYear: Int,
    var carColor: String,
    var carGearType: String,
    var carModel: String
) : Vehicles(4, carBrand, carYear, carModel) {
    fun carsMethod(speed: Int) {
        println("The $carBrand is a car that started production in $carYear.")
        println("The model,$carModel,in question is of a $carGearType type and can reach up to speeds of $speed miles per hour")
    }
}

class Tricycles(
    var startMethod: String,
    var seatNumbers: Int,
    var kekeYear: Int,
    var kekeBrand: String,
    var kekeModel: String
) : Vehicles(3, kekeBrand, kekeYear, kekeModel)
