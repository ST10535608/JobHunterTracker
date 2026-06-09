package com.makayla.jobhuntertracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

    lateinit var txtWeekday: TextView

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

        txtWeekday.text = days[index]

        btnSaveData.setOnClickListener {
            if (fillArrays()) {
                if (index < days.count() - 1) {
                    index++
                    txtWeekday.text = days[index]
                    clearInputs()
                    Toast.makeText(this, "Saved! Now enter ${days[index]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "All entries done! Tap View Details.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnClearData.setOnClickListener {
            clearData()
        }

        btnViewSummary.setOnClickListener {
            val intent = Intent(this, DetailedSummaryView::class.java)
            intent.putExtra("total_application", calculateTotalApplication())
            intent.putExtra("Average_Hours", calculateAverageHours())
            intent.putExtra("productive", mostProductiveDay())
            intent.putExtra("summary", completeSummary())
            startActivity(intent)

        }


    }

    fun fillArrays(): Boolean {
        val jobApplication = edtApplications.text.toString()
        val hoursSpentApplying = edtHoursSpent.text.toString()
        val interviewsInvited = edtInterviews.text.toString()
        val notesPerDay = edtNotes.text.toString()

        if (jobApplication.isEmpty() || hoursSpentApplying.isEmpty() || interviewsInvited.isEmpty() || notesPerDay.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }
        applications[index] = jobApplication.toInt()
        hoursSearched[index] = hoursSpentApplying.toInt()
        interviews[index] = interviewsInvited.toInt()
        notes[index] = notesPerDay
        return true
    }

    fun clearInputs () {
        edtApplications.text.clear()
        edtHoursSpent.text.clear()
        edtInterviews.text.clear()
        edtNotes.text.clear()
    }

    fun clearData() {
        applications = Array(7) { 0 }
        hoursSearched = Array(7) { 0 }
        interviews = Array(7) { 0 }
        notes = Array(7) { "" }
        index = 0
        txtWeekday.text = days[0]
        clearInputs()
        Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show()
    }

    fun calculateTotalApplication(): Int {
        var total = 0
        var counter = 0
        while (counter < applications.count()) {
            total += applications[counter]
            counter++
        }
        return total
    }

    fun calculateAverageHours(): Double {
        var total = 0
        var counter = 0
        while (counter < hoursSearched.count()) {
            total += hoursSearched[counter]
            counter++
        }
        return total.toDouble() / hoursSearched.count()
    }

    fun mostProductiveDay(): String {
        var max = applications[0]
        var maxIndex = 0
        var counter = 1
        while (counter < applications.count()) {
            if (applications[counter] > max) {
                max = applications[counter]
                maxIndex = counter
            }
            counter++
        }
        return "${days[maxIndex]} ($max)"
    }

    fun completeSummary(): String {
        var result = ""
        var counter = 0
        while (counter < days.count()) {
            result += "${days[counter]}: ${applications[counter]} / ${hoursSearched[counter]}\n / ${interviews[counter]}\n / ${notes[counter]}\n"
            counter++
        }
        return result
    }
}