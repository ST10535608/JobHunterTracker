package com.makayla.jobhuntertracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnStartActivity = findViewById<Button>(R.id.btnStart)
        val btnExitApp = findViewById<Button>(R.id.btnExit)

        // button which closes app
        btnExitApp.setOnClickListener {
            finishAffinity()
        }

        // button which allows user to move to next screen
        btnStartActivity.setOnClickListener {
            val intent = Intent(this, EnterDailyData::class.java)
            startActivity(intent)
        }
    }
}