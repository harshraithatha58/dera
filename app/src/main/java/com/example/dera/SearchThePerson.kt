package com.example.dera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SearchThePerson : AppCompatActivity() {
    lateinit var searchByName: Button
    lateinit var searchByFathersName: Button
    lateinit var searchByMobileNumber: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_the_person)

        searchByName = findViewById(R.id.searchByName)
        searchByFathersName = findViewById(R.id.searchByFathersName)
        searchByMobileNumber = findViewById(R.id.searchByNumber)

        searchByName.setOnClickListener {
            val intent = Intent(this, ShowDetails::class.java)
            intent.putExtra("selectedItem", "name")
            startActivity(intent)
        }

        searchByFathersName.setOnClickListener {
            val intent = Intent(this, ShowDetails::class.java)
            intent.putExtra("selectedItem", "father's Name")
            startActivity(intent)
        }

        searchByMobileNumber.setOnClickListener {
            val intent = Intent(this, ShowDetails::class.java)
            intent.putExtra("selectedItem", "Mobile Number")
            startActivity(intent)
        }
    }
}
