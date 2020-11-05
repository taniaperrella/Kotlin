package com.wcc.supertrunfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.activity_game_result.*

class GameResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_result)

        val playerOneName = intent.getStringExtra("player_one") ?: "Player One"
        val playerTwoName = intent.getStringExtra("player_two") ?: "Player Two"
        val chosenCriteria = intent.getStringExtra("criteria") ?: "velocity"

        val vehiculeOne = mapOf(
            "maxAcceleration" to "100",
            "acceleration0T010" to "120",
            "passengers" to "5",
            "doors" to "2",
            "style" to "sedã",
            "type" to "car"
        )

        val vehiculeTwo = mapOf(
            "maxAcceleration" to "50",
            "acceleration0T010" to "60",
            "passengers" to "2",
            "gears" to "7",
            "type" to "bicke"
        )

        val vehiculeThree = mapOf(
            "maxAcceleration" to "170",
            "acceleration0T010" to "40",
            "passengers" to "2",
            "weight" to "70",
            "type" to "motorcycle"
        )

        val vehiculeFour = mapOf(
            "maxAcceleration" to "130",
            "acceleration0T010" to "170",
            "passengers" to "4",
            "doors" to "2",
            "style" to "hatch",
            "type" to "car"
        )


        val vehiculeFive = mapOf(
            "maxAcceleration" to "30",
            "acceleration0T010" to "240",
            "passengers" to "1",
            "gears" to "4",
            "type" to "bike"
        )

        val driverOne = mapOf(
            "carXP" to "40",
            "bikeXP" to "60",
            "motorcycleXP" to "10",
            "carChampionships" to "2",
            "bikeChampionships" to "10",
            "motorcycleChampionships" to "0",
            "boldness" to "3",
            "defensiveDriving" to "4",
        )

        val driverTwo = mapOf(
            "carXP" to "90",
            "bikeXP" to "10",
            "motorcycleXP" to "30",
            "carChampionships" to "30",
            "bikeChampionships" to "0",
            "motorcycleChampionships" to "0",
            "boldness" to "2",
            "defensiveDriving" to "7",
        )

        val driverThree = mapOf(
            "carXP" to "50",
            "bikeXP" to "30",
            "motorcycleXP" to "80",
            "carChampionships" to "3",
            "bikeChampionships" to "7",
            "motorcycleChampionships" to "15",
            "boldness" to "6",
            "defensiveDriving" to "2",
        )

        val vehicles = listOf(vehiculeOne, vehiculeTwo, vehiculeThree, vehiculeFour, vehiculeFive)
        val drivers = listOf(driverOne, driverTwo, driverThree)

        val currentVehiclePlayerOne = vehicles.random()
        val currentVehiclePlayerTwo = vehicles.random()

        val currentDriverPlayerOne = drivers.random()
        val currentDriverPlayerTwo = drivers.random()

        val cardOneMaxVelocity =
            when (currentVehiclePlayerOne["type"]) {
                "car" ->
                    if (currentVehiclePlayerOne["style"] == "sedã") {
                        currentVehiclePlayerOne["maxAcceleration"]?.toInt() ?: 0
                    } else {
                        currentVehiclePlayerOne["maxAcceleration"]?.toInt() ?: 0 + 10
                    }

                "motorcycle" -> (1 / (currentVehiclePlayerOne["weight"]?.toInt()
                    ?: 1)) * (currentVehiclePlayerOne["maxAcceleration"]?.toInt() ?: 0)

                else -> (currentVehiclePlayerOne["maxAcceleration"]?.toInt()
                    ?: 1 * (currentDriverPlayerOne["boldness"]?.toInt() ?: 1))
            }

        val accelerationTimeCardOne = (currentVehiclePlayerOne["accelerationTime"]?.toInt()
            ?: 1) * (1 / (currentDriverPlayerOne["accelerationTime"]?.toInt() ?: 1))

        val passengersCardOne = (currentVehiclePlayerOne["passengers"]?.toInt()
            ?: 0) * (1 + (currentDriverPlayerOne["defensiveDriving"]?.toInt() ?: 0))

        val xPCardOne = when (currentVehiclePlayerOne["type"]) {
            "car" -> currentDriverPlayerOne["carXP"]?.toInt() ?: 0
            "motorcycle" -> currentDriverPlayerOne["motorcycleXP"]?.toInt() ?: 0
            else -> currentDriverPlayerOne["bikeXP"]?.toInt() ?: 0
        }

        val cardTwoMaxVelocity =
            when (currentVehiclePlayerTwo["type"]) {
                "car" ->
                    if (currentVehiclePlayerTwo["style"] == "sedã") {
                        currentVehiclePlayerTwo["maxAcceleration"]?.toInt() ?: 0
                    } else {
                        currentVehiclePlayerTwo["maxAcceleration"]?.toInt() ?: 0 + 10
                    }

                "motorcycle" -> (1 / (currentVehiclePlayerTwo["weight"]?.toInt()
                    ?: 1)) * (currentVehiclePlayerTwo["maxAcceleration"]?.toInt() ?: 0)

                else -> (currentVehiclePlayerTwo["maxAcceleration"]?.toInt()
                    ?: 1 * (currentDriverPlayerTwo["boldness"]?.toInt() ?: 1))
            }

        val accelerationTimeCardTwo = (currentVehiclePlayerOne["accelerationTime"]?.toInt()
            ?: 1) * (1 / (currentDriverPlayerOne["accelerationTime"]?.toInt() ?: 1))

        val passengersCardTwo = (currentVehiclePlayerOne["passengers"]?.toInt()
            ?: 0) * (1 + (currentDriverPlayerOne["defensiveDriving"]?.toInt() ?: 0))

        val xPCardTwo = when (currentVehiclePlayerOne["type"]) {
            "car" -> currentDriverPlayerOne["carXP"]?.toInt() ?: 0
            "motorcycle" -> currentDriverPlayerOne["motorcycleXP"]?.toInt() ?: 0
            else -> currentDriverPlayerOne["bikeXP"]?.toInt() ?: 0
        }


        val winner = when(chosenCriteria) {
            "velocity" ->
                if(cardOneMaxVelocity > cardTwoMaxVelocity) {
                    playerOneName
                } else {
                    playerTwoName
                }
            "acceleration" ->
                if(accelerationTimeCardOne > accelerationTimeCardTwo) {
                    playerOneName
                } else {
                    playerTwoName
                }
            "passengers" ->
                if(passengersCardOne > passengersCardTwo) {
                    playerOneName
                } else {
                    playerTwoName
                }
            else ->
                if(xPCardOne > xPCardTwo) {
                    playerOneName
                } else {
                    playerTwoName
                }
        }

        winnerLabel.text = "The winner is $winner"
    }
}