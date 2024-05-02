package com.example.dera

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class NewFormEntry : AppCompatActivity() {
    lateinit var imageView : ImageView
    lateinit var imageUri: Uri
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it!!
        imageView.setImageURI(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_form_entry)
        imageView = findViewById(R.id.imgViewer)
        val pic = findViewById<Button>(R.id.btnCamera)
        pic.setOnClickListener{
            contract.launch("image/*")
        }

        val btnNext = findViewById<Button>(R.id.nextButton)
        btnNext.setOnClickListener {
            sendData()
        }

    }

    private fun isValidMobileNumber(number: String): Boolean {
        val regex = """^[6789]\d{9}$""".toRegex()
        return regex.matches(number)
    }

    private fun sendData() {
        val identificationText = findViewById<EditText>(R.id.identification).text.toString()
        val nameText = findViewById<EditText>(R.id.name).text.toString()
        val fathersNameText = findViewById<EditText>(R.id.fathersName).text.toString()
        val addressText = findViewById<EditText>(R.id.address).text.toString()
        val religionText = findViewById<EditText>(R.id.religion).text.toString()
        val maritalStatusText = findViewById<EditText>(R.id.maritialStatus).text.toString()
        val mobileNumberText = findViewById<EditText>(R.id.mobileNumber).text.toString()
        val designationText = findViewById<EditText>(R.id.designation).text.toString()
        val durationText = findViewById<EditText>(R.id.duration).text.toString()
        val routeUsedText = findViewById<EditText>(R.id.routeUsed).text.toString()
        val placeVisitedLastYearText = findViewById<EditText>(R.id.placeVisitedLastYear).text.toString()

        if (mobileNumberText.isNotEmpty() && isValidMobileNumber(mobileNumberText) && imageUri != null) {
            val intent = Intent(this, FamilyDetails::class.java).apply {
                putExtra("imageUri", imageUri.toString())
                putExtra("identification", identificationText)
                putExtra("name", nameText)
                putExtra("fathersName", fathersNameText)
                putExtra("address", addressText)
                putExtra("religion", religionText)
                putExtra("maritalStatus", maritalStatusText)
                putExtra("mobileNumber", mobileNumberText)
                putExtra("designation", designationText)
                putExtra("duration", durationText)
                putExtra("routeUsed", routeUsedText)
                putExtra("placeVisitedLastYear", placeVisitedLastYearText)
            }
            startActivity(intent)
        } else {
            val message = "Invalid Mobile Number"
            showToast(message)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
