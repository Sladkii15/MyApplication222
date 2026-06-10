package com.example.laba3chat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var messagesContainer: LinearLayout
    private lateinit var inputText: EditText
    private lateinit var sendButton: Button
    private lateinit var scrollView: ScrollView
    private lateinit var photoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollView = findViewById(R.id.scrollView)
        messagesContainer = findViewById(R.id.messagesContainer)
        inputText = findViewById(R.id.inputText)
        sendButton = findViewById(R.id.sendBtn)
        photoButton = findViewById(R.id.photo)

        sendButton.setOnClickListener {
            val messageText = inputText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                addMessage(messageText)
                inputText.text.clear()
                scrollView.post { scrollView.fullScroll(View.FOCUS_DOWN) }
            }
        }

        photoButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addMessage(text: String) {
        val messageView = TextView(this).apply {
            this.text = text
            setPadding(40, 20, 40, 20)
            setBackgroundResource(android.R.drawable.dialog_holo_light_frame)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 8, 16, 8)
            }
        }
        messagesContainer.addView(messageView)
    }
}