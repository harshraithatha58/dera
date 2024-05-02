package com.example.dera

import android.annotation.SuppressLint
import android.content.ContentValues
//import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
//import android.util.Log
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID


class FamilyDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_details)

        val saveButton = findViewById<Button>(R.id.FsaveButton)

        saveButton.setOnClickListener {
            shareData()
        }
    }
    private fun shareData() {
        val imageUriString = intent.getStringExtra("imageUri")
        val image = Uri.parse(imageUriString)
        val identification = intent.getStringExtra("identification") ?: ""
        val name = intent.getStringExtra("name") ?: ""
        val fathersName = intent.getStringExtra("fathersName") ?: ""
        val address = intent.getStringExtra("address") ?: ""
        val religion = intent.getStringExtra("religion") ?: ""
        val maritalStatus = intent.getStringExtra("maritalStatus") ?: "" // Corrected typo
        val mobileNumber = intent.getStringExtra("mobileNumber") ?: ""
        val designation = intent.getStringExtra("designation") ?: ""
        val duration = intent.getStringExtra("duration") ?: ""
        val routeUsed = intent.getStringExtra("routeUsed") ?: ""
        val placeVisitedLastYear = intent.getStringExtra("placeVisitedLastYear") ?: ""

        val fName = findViewById<EditText>(R.id.fName).text.toString()
        val fAge = findViewById<EditText>(R.id.fAge).text.toString()
        val fGender = findViewById<EditText>(R.id.fGender).text.toString()
        val fRelation = findViewById<EditText>(R.id.fRelation).text.toString()
        val fAddharNumber = findViewById<EditText>(R.id.fAddharNumber).text.toString()
        val fMobileNumber = findViewById<EditText>(R.id.fMobileNumber).text.toString()

        val data = arrayOf(
            identification, name, fathersName, address, religion, maritalStatus, mobileNumber,
            designation, duration, routeUsed, placeVisitedLastYear, fName, fAge, fGender,
            fRelation, fAddharNumber, fMobileNumber
        )

        // Pass image URI directly to postData function
        postData(data, image)
    }

    private fun postData(data: Array<String>, imageUri: Uri) {
        val fileDir = applicationContext.filesDir
        val file = File(fileDir, "image.png")
        val inputStream = contentResolver.openInputStream(imageUri)
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("image", file.name, requestBody)

        val identification = data[0].toRequestBody("text/plain".toMediaTypeOrNull())
        val name = data[1].toRequestBody("text/plain".toMediaTypeOrNull())
        val fathersName = data[2].toRequestBody("text/plain".toMediaTypeOrNull())
        val address = data[3].toRequestBody("text/plain".toMediaTypeOrNull())
        val religion = data[4].toRequestBody("text/plain".toMediaTypeOrNull())
        val maritalStatus = data[5].toRequestBody("text/plain".toMediaTypeOrNull())
        val mobileNumber = data[6].toRequestBody("text/plain".toMediaTypeOrNull())
        val designation = data[7].toRequestBody("text/plain".toMediaTypeOrNull())
        val duration = data[8].toRequestBody("text/plain".toMediaTypeOrNull())
        val routeUsed = data[9].toRequestBody("text/plain".toMediaTypeOrNull())
        val placeVisitedLastYear = data[10].toRequestBody("text/plain".toMediaTypeOrNull())
        val fName = data[11].toRequestBody("text/plain".toMediaTypeOrNull())
        val fAge = data[12].toRequestBody("text/plain".toMediaTypeOrNull())
        val fGender = data[13].toRequestBody("text/plain".toMediaTypeOrNull())
        val fRelation = data[14].toRequestBody("text/plain".toMediaTypeOrNull())
        val fAddharNumber = data[15].toRequestBody("text/plain".toMediaTypeOrNull())
        val fMobileNumber = data[16].toRequestBody("text/plain".toMediaTypeOrNull())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.sohangeducation.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UploadService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofit.uploadImage(
                    part,
                    identification,
                    name,
                    fathersName,
                    address,
                    religion,
                    maritalStatus,
                    mobileNumber,
                    designation,
                    duration,
                    routeUsed,
                    placeVisitedLastYear,
                    fName,
                    fAge,
                    fGender,
                    fRelation,
                    fAddharNumber,
                    fMobileNumber
                )
                Log.d("success", response.toString())
                goToThreeButtonPage()
            } catch (e: Exception) {
                Log.e("error", "Error uploading image: ${e.message}")
                e.printStackTrace()
            }
        }
    }


    private fun goToThreeButtonPage() {
        val intent =  Intent(this,ThreeButtonPage::class.java)
        startActivity(intent)

    }

}



//    private fun storeDataOffline() {
//        // Get data from Intent
//        val identification = intent.getStringExtra("identification") ?: ""
//        val name = intent.getStringExtra("name") ?: ""
//        val fathersName = intent.getStringExtra("fathersName") ?: ""
//        val address = intent.getStringExtra("address") ?: ""
//        val religion = intent.getStringExtra("religion") ?: ""
//        val maritalStatus = intent.getStringExtra("maritalStatus") ?: ""
//        val mobileNumber = intent.getStringExtra("mobileNumber") ?: ""
//        val designation = intent.getStringExtra("designation") ?: ""
//        val duration = intent.getStringExtra("duration") ?: ""
//        val routeUsed = intent.getStringExtra("routeUsed") ?: ""
//        val placeVisitedLastYear = intent.getStringExtra("placeVisitedLastYear") ?: ""
//        val fName = findViewById<EditText>(R.id.fName).text.toString()
//        val fAge = findViewById<EditText>(R.id.fAge).text.toString()
//        val fGender = findViewById<EditText>(R.id.fGender).text.toString()
//        val fRelation = findViewById<EditText>(R.id.fRelation).text.toString()
//        val fAddharNumber = findViewById<EditText>(R.id.fAddharNumber).text.toString()
//        val fMobileNumber = findViewById<EditText>(R.id.fMobileNumber).text.toString()
//
//        // Get the bitmap from the Intent extras
//        val bitmap: Bitmap? = intent.getParcelableExtra("imageBitmap")
//
//// Check if bitmap is not null
//        bitmap?.let {
//            // Save the bitmap to a file in external storage
//            val imagePath = saveBitmapToFile(bitmap)
//            // Check if imagePath is not null before proceeding
//            imagePath?.let {
//                // Store data in the local database
//                val db = dbHelper.writableDatabase
//                val values = ContentValues().apply {
//                    put("identification", identification)
//                    put("name", name)
//                    put("fathers_name", fathersName)
//                    put("address", address)
//                    put("religion", religion)
//                    put("marital_status", maritalStatus)
//                    put("mobile_number", mobileNumber)
//                    put("designation", designation)
//                    put("duration", duration)
//                    put("route_used", routeUsed)
//                    put("place_visited_last_year", placeVisitedLastYear)
//                    put("f_name", fName)
//                    put("f_age", fAge)
//                    put("f_gender", fGender)
//                    put("f_relation", fRelation)
//                    put("f_addhar_number", fAddharNumber)
//                    put("f_mobile_number", fMobileNumber)
//                    put("image_address", imagePath) // Store the image path in the database
//                }
//
//                // Insert data into the database
//                val newRowId = db.insert("users", null, values)
//
//                if (newRowId != -1L) {
//                    Toast.makeText(this, "Data stored offline successfully", Toast.LENGTH_SHORT).show()
//                    // Optionally, navigate to the next activity
//                    goToThreeButtonPage()
//                    return
//                }
//            }
//        }
//
//        // Display a toast message if data storage fails
//        Toast.makeText(this, "Failed to store data offline", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun saveBitmapToFile(bitmap: Bitmap?): String? {
//        bitmap ?: return null // Return null if bitmap is null
//
//        val fileName = "${UUID.randomUUID()}.jpg"
//        val directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        val file = File(directory, fileName)
//
//        return try {
//            val fos = FileOutputStream(file)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
//            fos.flush()
//            fos.close()
//            file.absolutePath
//        } catch (e: IOException) {
//            e.printStackTrace()
//            null
//        }
//    }

//
//    private fun storeDataOffline() {
//        val imageUriString = intent.getStringExtra("imageUri") ?: ""
//        val identification = intent.getStringExtra("identification") ?: ""
//        val name = intent.getStringExtra("name") ?: ""
//        val fathersName = intent.getStringExtra("fathersName") ?: ""
//        val address = intent.getStringExtra("address") ?: ""
//        val religion = intent.getStringExtra("religion") ?: ""
//        val maritalStatus = intent.getStringExtra("maritalStatus") ?: ""
//        val mobileNumber = intent.getStringExtra("mobileNumber") ?: ""
//        val designation = intent.getStringExtra("designation") ?: ""
//        val duration = intent.getStringExtra("duration") ?: ""
//        val routeUsed = intent.getStringExtra("routeUsed") ?: ""
//        val placeVisitedLastYear = intent.getStringExtra("placeVisitedLastYear") ?: ""
//        val fName = findViewById<EditText>(R.id.fName).text.toString()
//        val fAge = findViewById<EditText>(R.id.fAge).text.toString()
//        val fGender = findViewById<EditText>(R.id.fGender).text.toString()
//        val fRelation = findViewById<EditText>(R.id.fRelation).text.toString()
//        val fAddharNumber = findViewById<EditText>(R.id.fAddharNumber).text.toString()
//        val fMobileNumber = findViewById<EditText>(R.id.fMobileNumber).text.toString()
//
//// Save the image to external storage
//        val imageUri = Uri.parse(imageUriString)
//        val imagePath = imageUri.toString() // Store the URI as the image path
//
//        val db = dbHelper.writableDatabase
//        val values = ContentValues().apply {
//            put("identification", identification)
//            put("name", name)
//            put("fathers_name", fathersName)
//            put("address", address)
//            put("religion", religion)
//            put("marital_status", maritalStatus)
//            put("mobile_number", mobileNumber)
//            put("designation", designation)
//            put("duration", duration)
//            put("route_used", routeUsed)
//            put("place_visited_last_year", placeVisitedLastYear)
//            put("f_name", fName)
//            put("f_age", fAge)
//            put("f_gender", fGender)
//            put("f_relation", fRelation)
//            put("f_addhar_number", fAddharNumber)
//            put("f_mobile_number", fMobileNumber)
//            put("image_address", imagePath) // Store the image path in the database
//        }
//
//        val newRowId = db.insert("users", null, values)
//
//        if (newRowId != -1L) {
//            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//            // Optionally, navigate to the next activity
//            goToThreeButtonPage()
//        } else {
//            Toast.makeText(this, "Failed to insert data", Toast.LENGTH_SHORT).show()
//        }
//    }







