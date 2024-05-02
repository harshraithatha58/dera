package com.example.dera

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var userNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        https://coolors.co/palette/606c38-283618-fefae0-f1d7b3-6A994E
//        val rootView = findViewById<View>(android.R.id.content)
//        rootView.setBackgroundResource(R.drawable.img)
        // Initialize EditText fields
        userNameEditText = findViewById(R.id.userName)
        passwordEditText = findViewById(R.id.password)

        button = findViewById(R.id.button)

        // Set onClickListener for the button
        button.setOnClickListener {
            createToast()
        }
    }

    private fun createToast() {
        // Get text from EditText field
        val userName = userNameEditText.text.toString()
        val password = passwordEditText.text.toString()

        if(userName!="abc"||password!="123"){
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this, ThreeButtonPage::class.java)
            intent.putExtra(ThreeButtonPage.USER_ID,userName)
            intent.putExtra(ThreeButtonPage.USER_ID,userName)
            startActivity(intent)
            finish()
        }

    }
}