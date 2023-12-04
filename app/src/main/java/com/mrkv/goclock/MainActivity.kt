package com.mrkv.goclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: ImageButton
    private lateinit var timeOfBlack: TextView
    private lateinit var timeOfWhite: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.start_button)
        timeOfBlack = findViewById(R.id.black_clock_text_view)
        timeOfWhite = findViewById(R.id.white_clock_text_view)

        val whiteTimer = object : CountDownTimer(3600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeOfWhite.text = setTimer(millisUntilFinished)
            }

            override fun onFinish() {
                stopLockTask()
            }
        }

        val blackTimer = object : CountDownTimer(3600000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeOfBlack.text = setTimer(millisUntilFinished)
            }

            override fun onFinish() {
                stopLockTask()
            }
        }

        startButton.setOnClickListener {
            blackTimer.start()
        }

        timeOfBlack.setOnClickListener {
            whiteTimer.start()
            blackTimer.cancel()
        }

        timeOfWhite.setOnClickListener {
            blackTimer.start()
            whiteTimer.cancel()
        }

    }

    private fun setTimer(millisUntilFinished: Long): String {
        var timeInMillis = millisUntilFinished
        val secondsInMillis: Long = 1000
        val minutesInMillis = secondsInMillis * 60
        val hoursInMillis = minutesInMillis * 60
        val hours = timeInMillis / hoursInMillis
        timeInMillis %= hoursInMillis
        val minutes = timeInMillis / minutesInMillis
        timeInMillis %= minutesInMillis
        val seconds = timeInMillis / secondsInMillis
        timeInMillis %= hoursInMillis
        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
}