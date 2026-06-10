package com.example.myapplication222

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var textViewDate: TextView

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val dateMillis = result.data?.getLongExtra("SELECTED_DATE", 0) ?: 0
            if (dateMillis != 0L) {
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val dateString = dateFormat.format(dateMillis)
                textViewDate.text = "Выбрана дата: $dateString"
                Toast.makeText(this, "Дата: $dateString", Toast.LENGTH_LONG).show()
            } else {
                textViewDate.text = "Дата не была выбрана"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextText)
        val button = findViewById<Button>(R.id.button)
        textViewDate = findViewById(R.id.textViewDate)

        button.setOnClickListener {
            val name = editTextName.text.toString().ifEmpty { "Гость" }
            val intent = Intent(this, MainActivity2::class.java).apply {
                putExtra("USER_NAME", name)
            }
            launcher.launch(intent)
        }
    }
}