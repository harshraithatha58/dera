package com.example.dera
//import com.bumptech.glide.Glide
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONException
import org.json.JSONObject

class ShowDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        val button = this.findViewById<Button>(R.id.fetchPerson)
        val selectedItem = intent.getStringExtra("selectedItem")
        val x = when (selectedItem) {
            "name" -> "name"
            "father's Name" -> "fathersName"
            "Mobile Number" -> "mobileNumber"
            else -> {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                "Error"
            }
        }
        val btn = findViewById<Button>(R.id.fetchPerson)
        button.setOnClickListener {
            val value = findViewById<EditText>(R.id.searchInput).text.toString()
            fetchDetails(x, value, btn)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fetchDetails(param: String, value: String,btn:Button) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.sohangeducation.in/search.php?$param=$value"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                // Handle API response here

                btn.visibility = View.INVISIBLE
                val header1 = findViewById<TextView>(R.id.personalDetails)
                header1.visibility = View.VISIBLE

                val nameOut = findViewById<TextView>(R.id.nameOutput)
                nameOut.visibility = View.VISIBLE
                val res = response.optString("name")
                val x1 = nameOut.text

                nameOut.text = "$x1 > $res"

                val fathersNameOutput = findViewById<TextView>(R.id.fathersNameOutput)
                fathersNameOutput.visibility = View.VISIBLE
                val x2 = fathersNameOutput.text
                val y2 = response.optString("fathers_name")
                fathersNameOutput.text = "$x2 > $y2"

                val addressOutput = findViewById<TextView>(R.id.addressOutput)
                addressOutput.visibility = View.VISIBLE
                val x3 = addressOutput.text
                val y3 = response.optString("address")
                addressOutput.text = "$x3 > $y3"

                val religionOutput = findViewById<TextView>(R.id.relegionOutput)
                religionOutput.visibility = View.VISIBLE
                val x4 = religionOutput.text
                val y4 = response.optString("religion")
                religionOutput.text = "$x4 > $y4"

                val maritalStatusOutput = findViewById<TextView>(R.id.maretialStatusOutput)
                maritalStatusOutput.visibility = View.VISIBLE
                val x5 = maritalStatusOutput.text
                val y5 = response.optString("marital_status")
                maritalStatusOutput.text = "$x5 > $y5"

                val mobileNumberOutput = findViewById<TextView>(R.id.mobileNumberOutput)
                mobileNumberOutput.visibility = View.VISIBLE
                val x6 = mobileNumberOutput.text
                val y6 = response.optString("mobile_number")
                mobileNumberOutput.text = "$x6 > $y6"

                val designationOutput = findViewById<TextView>(R.id.designationOutput)
                designationOutput.visibility = View.VISIBLE
                val x7 = designationOutput.text
                val y7 = response.optString("designation")
                designationOutput.text = "$x7 > $y7"

                val durationOut = findViewById<TextView>(R.id.durationOutput)
                durationOut.visibility = View.VISIBLE
                val x21 = durationOut.text
                val y21 = response.optString("duration")
                durationOut.text = "$x21 > $y21"

                val routeUsedOutput = findViewById<TextView>(R.id.routeUsedOutput)
                routeUsedOutput.visibility = View.VISIBLE
                val x8 = routeUsedOutput.text
                val y8 = response.optString("route_used")
                routeUsedOutput.text = "$x8 > $y8"

                val placeVisitedLastYear = findViewById<TextView>(R.id.placeVisitedLastYearOutput)
                placeVisitedLastYear.visibility = View.VISIBLE
                val x0 = placeVisitedLastYear.text
                val y0 = response.optString("place_visited_last_year")
                placeVisitedLastYear.text = "$x0 > $y0"

                val header2 = findViewById<TextView>(R.id.familyDatails)
                header2.visibility = View.VISIBLE

                val fNameOutput = findViewById<TextView>(R.id.fNameOutput)
                fNameOutput.visibility = View.VISIBLE
                val x9 = fNameOutput.text
                val y9 = response.optString("f_name")
                fNameOutput.text = "$x9 > $y9"

                val ageOutput = findViewById<TextView>(R.id.ageOutput)
                ageOutput.visibility = View.VISIBLE
                val x10 = ageOutput.text
                val y10 = response.optString("f_age")
                ageOutput.text = "$x10 > $y10"

                val genderOutput = findViewById<TextView>(R.id.genderOutput)
                genderOutput.visibility = View.VISIBLE
                val x11 = genderOutput.text
                val y11 = response.optString("f_gender")
                genderOutput.text = "$x11 > $y11"

                val relationOutput = findViewById<TextView>(R.id.relationOutput)
                relationOutput.visibility = View.VISIBLE
                val x12 = relationOutput.text
                val y12 = response.optString("f_relation")
                relationOutput.text = "$x12 > $y12"

                val aadharCardOutput = findViewById<TextView>(R.id.addharNumberOutput)
                aadharCardOutput.visibility = View.VISIBLE
                val x13 = aadharCardOutput.text
                val y13 = response.optString("f_addhar_number")
                aadharCardOutput.text = "$x13 > $y13"

                val fMobileNumberOutput = findViewById<TextView>(R.id.familyMobileNumberOutput)
                fMobileNumberOutput.visibility = View.VISIBLE
                val x14 = fMobileNumberOutput.text
                val y14 = response.optString("f_mobile_number")
                fMobileNumberOutput.text = "$x14 > $y14"

                val imgView = findViewById<ImageView>(R.id.imageView)
                val imgAddr = response.optString("image_address")
                Glide.with(this)
                    .load(imgAddr)
                    .into(imgView)
                imgView.visibility = View.VISIBLE

            },
            { error ->
                // Handle errors
                Log.e("Volley Error", "Error occurred", error)
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

}
//    @SuppressLint("Range", "Recycle", "CutPasteId")
//    private fun fetchDetailsOffline(param: String, value: String) {
//        // Get readable database
//        val db = dbHelper.readableDatabase
//
//        // Construct SQL query based on parameter and value
//        val sqlQuery = when {
//            param == "mobile_number" -> "SELECT name, fathers_name, address, religion, marital_status, mobile_number, designation, duration, route_used, place_visited_last_year, f_name, f_age, f_gender, f_relation, f_addhar_number, f_mobile_number, image_address FROM users WHERE $param = '$value'"
//            else -> "SELECT name, fathers_name, address, religion, marital_status, mobile_number, designation, duration, route_used, place_visited_last_year, f_name, f_age, f_gender, f_relation, f_addhar_number, f_mobile_number, image_address FROM users WHERE $param = '$value'"
//        }
//        Log.d("TAG", "SQL Query: $sqlQuery")
//
//        // Execute query and retrieve cursor
//        val cursor: Cursor = db.rawQuery(sqlQuery, null)
//
//        var resultString = ""
//        val myView = findViewById<TextView>(R.id.textView)
//        myView.visibility = View.VISIBLE
//
//        button.visibility = View.INVISIBLE
//        searchInput.isClickable = false
//        searchInput.isFocusable = false
//
//        // Get the column index of the image_address column
//        val imageAddressIndex = cursor.getColumnIndex("image_address")
//
//        // Iterate through cursor and append data to resultString
//        while (cursor.moveToNext()) {
//            val columnCount = cursor.columnCount
//            for (i in 0 until columnCount) {
//                val columnName = cursor.getColumnName(i)
//                val columnValue = cursor.getString(i)
//
//                resultString += "$columnName: $columnValue\n"
//                Log.d("OUTPUT BY QUERY", "SQL Query: $columnName: $columnValue ")
//            }
//
//            resultString += "\n" // Add a new line to separate records
//
//            // Get the image URL from the cursor using the index
//            val imageUrl = cursor.getString(imageAddressIndex)
//
//            // Load image using Glide
//            val imageView = findViewById<ImageView>(R.id.imageView)
//            Glide.with(this)
//                .load(imageUrl)
//                .apply(RequestOptions()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL) // Caching strategy
//                )
//                .into(imageView)
//        }
//
//        // Close cursor and database
//        cursor.close()
//        db.close()
//
//        // Get reference to TextView to display fetched data
//        val textView = findViewById<TextView>(R.id.textView)
//        // Set text of TextView with fetched data or "No records found" if no data is fetched
//        textView.text = resultString.ifEmpty { "No records found" }
//    }
