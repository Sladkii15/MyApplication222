package com.example.myapplication222

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity2 : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var imageView: ImageView
    private var selectedDateMillis: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textView = findViewById<TextView>(R.id.textView2)
        val btnOk = findViewById<Button>(R.id.btnOk)

        val btnBack = findViewById<Button>(R.id.button2)
        calendarView = findViewById(R.id.calendarView)
        imageView = findViewById(R.id.imageViewPhoto)

        val userName = intent.getStringExtra("USER_NAME") ?: "Гость"
        textView.text = "Привет, $userName!"

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            selectedDateMillis = calendar.timeInMillis
        }

        btnOk.setOnClickListener {
            var dateToSend = selectedDateMillis
            if (dateToSend == 0L) {
                dateToSend = calendarView.date
            }
            val resultIntent = Intent().apply {
                putExtra("SELECTED_DATE", dateToSend)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}