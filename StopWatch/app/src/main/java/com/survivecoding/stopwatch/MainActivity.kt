package com.survivecoding.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var timerTask: Timer? = null
    private var time = 0
    private var isRunning = false;
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            if (isRunning) {
                pause()
            } else {
                start()
            }
            isRunning = !isRunning
        }

        button.setOnClickListener { recordLapTime() }
        resetFab.setOnClickListener { reset() }
    }

    private fun start() {
        fab.setImageResource(R.drawable.ic_pause_black_24dp)
        timerTask = timer(period = 10) {
            time++;
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    private fun pause() {
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }

    private fun recordLapTime() {
        val lapTime = this.time
        var textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"
        labLayout.addView(textView, 0)
        lap++
    }

    private fun reset() {
        timerTask?.cancel()

        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)

//        secTextView.text = "0"
//        milliTextView.text = "00"
//        runOnUiThread {
//            secTextView.text = "0"
//            milliTextView.text = "00"
//        }
        timer (period = 100) {
            println("timer in")
            runOnUiThread {
                println("setText start")
                secTextView.text = "0"
                milliTextView.text = "00"
                println("setText finish")
            }
            if (secTextView.text == "0" && milliTextView.text == "00") {
                println("cancel now")
                this.cancel()
                println("cancel finish")
            }
        }

        labLayout.removeAllViews()
        lap = 1
    }
}