package com.wcc.supertrunfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose_criteria.*
import kotlinx.android.synthetic.main.activity_main.*

class ChooseCriteriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_criteria)

        val playerOneName = intent.getStringExtra("player_one") ?: "Player One"
        val playerTwoName = intent.getStringExtra("player_two") ?: "Player Two"

        velocityButton.setOnClickListener {
            val intent = Intent(this, GameResultActivity::class.java)
            intent.putExtra("player_one", playerOneName)
            intent.putExtra("player_two", playerTwoName)
            intent.putExtra("criteria", "velocity")
            startActivity(intent)
        }

        accelerationTimeButton.setOnClickListener {
            val intent = Intent(this, GameResultActivity::class.java)
            intent.putExtra("player_one", playerOneName)
            intent.putExtra("player_two", playerTwoName)
            intent.putExtra("criteria", "acceleration")
            startActivity(intent)
        }

        passengersButton.setOnClickListener {
            val intent = Intent(this, GameResultActivity::class.java)
            intent.putExtra("player_one", playerOneName)
            intent.putExtra("player_two", playerTwoName)
            intent.putExtra("criteria", "passengers")
            startActivity(intent)
        }

        xPButton.setOnClickListener {
            val intent = Intent(this, GameResultActivity::class.java)
            intent.putExtra("player_one", playerOneName)
            intent.putExtra("player_two", playerTwoName)
            intent.putExtra("criteria", "xp")
            startActivity(intent)
        }
    }
}