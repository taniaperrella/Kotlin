package com.wcc.supertrunfo.interactors

import com.wcc.supertrunfo.entities.Driver
import com.wcc.supertrunfo.entities.Player
import com.wcc.supertrunfo.entities.Vehicle

class Card(
        private val vehicle: Vehicle,
        private val driver: Driver,
        private val player: Player = Player("player")
) {

    val label: String = "Olá ${player.name}"
    val maxVelocity: Int = initMaxVelocity()
    val accelerationTime: Int = initAccelerationTime()
    val passengers: Int  = initPassengers()
    val xp: Int = initXP()

    private fun initMaxVelocity(): Int {
        return when (vehicle.type) {
            "car" ->
                if (vehicle.style == "sedã") {
                    vehicle.maxAcceleration
                } else {
                    vehicle.maxAcceleration + 10
                }
            "motorcycle" -> vehicle.weight * vehicle.maxAcceleration
            "bike" -> vehicle.maxAcceleration * driver.boldness
            else -> 0
        }
    }

    private fun initAccelerationTime(): Int {
        return vehicle.accelerationTime
    }

    private fun initPassengers(): Int {
        return vehicle.passengers * driver.defensiveDriving
    }

    private fun initXP(): Int {
        return when (vehicle.type) {
            "car" -> driver.carXP
            "motorcycle" -> driver.motorcycleXP
            "bike" -> driver.bikeXP
            else -> 0
        }
    }
}


