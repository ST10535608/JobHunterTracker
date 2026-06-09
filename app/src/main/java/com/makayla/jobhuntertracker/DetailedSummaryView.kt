package com.makayla.jobhuntertracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedSummaryView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_summary_view)

        val txtResults = findViewById<TextView>(R.id.txtResults)
        val txtTotalApplication = findViewById<TextView>(R.id.txtTotalApplication)
        val txtMostProductive = findViewById<TextView>(R.id.txtMostProductive)
        val txtAverageHours = findViewById<TextView>(R.id. txtAverageHours)
        val txtPersonalFeedback = findViewById<TextView>(R.id.txtPersonalFeedback)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val total = intent.extras?.getInt("total_application") ?: 0
        val highest = intent.extras?.getString("productive")?: ""
        val average = intent.extras?.getDouble("Average_Hours")?: 0.0
        val summary = intent.extras?.getString("summary")?: ""

        txtResults.text = summary
        txtTotalApplication.text = "Total Applications sent $total"
        txtMostProductive.text = "Most applications sent out $highest"
        txtAverageHours.text = "Your average search per day was $average"

        txtPersonalFeedback.text = when {
            total > 30 -> "Outstanding effort! You applied to over 30 positions this week. Keep it up!"
            total 15 <= 30 -> "Good work! You are making consistent progress in your job search"
                else -> "Keep pushing! Try to increase your daily applications next week."
        }

        btnBack.setOnClickListener {
            finish()
        }

    }
}