package com.wcc.supertrunfo.entities

data class Vehicle(
    val maxAcceleration: Int,
    val accelerationTime: Int,
    val passengers: Int,
    val weight: Int,
    val doors: Int,
    val style: String,
    val gears: Int,
    val type: String
)