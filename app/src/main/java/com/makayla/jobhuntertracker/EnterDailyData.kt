package com.makayla.jobhuntertracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EnterDailyData : AppCompatActivity() {

    var days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    var applications = Array(7) { 0 }

    var hoursSearched = Array(7) { 0 }

    var interviews = Array(7) { 0 }

    var notes = Array(7) { "" }

    var index = 0

    lateinit var edtApplications: EditText

    lateinit var edtHoursSpent: EditText

    lateinit var edtInterviews: EditText

    lateinit var edtNotes: EditText

    lateinit var txtWeekday: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_daily_data)

        edtApplications = findViewById(R.id.edtTxtApplications)
        edtHoursSpent = findViewById(R.id.edtHoursSpent)
        edtInterviews = findViewById(R.id.edtInterviewCount)
        edtNotes = findViewById(R.id.edttxtNotes)
        txtWeekday = findViewById(R.id.txtDay)

        val btnSaveData = findViewById<Button>(R.id.btnSaveData)
        val btnClearData = findViewById<Button>(R.id.btnClearData)
        val btnViewSummary = findViewById<Button>(R.id.btnViewSummary)


    }
}