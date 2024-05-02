package com.example.dera

//import android.widget.TextView
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class ThreeButtonPage : AppCompatActivity() {
    companion object {
        const val USER_ID= "userNameExtra"
        const val NETWORK_STATUS = "networkStatus"
    }

    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkChangeReceiver = NetworkChangeReceiver(this)
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)


        setContentView(R.layout.activity_three_button_page)
        val newEntryBtn = findViewById<Button>(R.id.newEntery)
        newEntryBtn.setOnClickListener{
            val intent = Intent(this,NewFormEntry::class.java)

            startActivity(intent)
        }
        val searchBtn = findViewById<Button>(R.id.search)
        searchBtn.setOnClickListener{
            val intent  = Intent(this,SearchThePerson::class.java)
            startActivity(intent)
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

//    fun onNetworkAvailable() {
//        networkState = "true"
//
////        Toast.makeText(this,"avi gyu", Toast.LENGTH_SHORT).show()
//        // Do something when network is available
//    }
//
//    fun onNetworkUnavailable() {
//        networkState = "false"
////        Toast.makeText(this,"vayu gyu", Toast.LENGTH_SHORT).show()
//        // Do something when network is unavailable
//    }


}

//    private fun checkDataInRemoteDatabase(data: Map<String, String>) {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.sohangeducation.in/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(syncService::class.java)
//
//        // Get image file from URI
//        val imageUrlString = data["image_address"]
//        val imagePart = if (imageUrlString != null) {
//            val imageFile = File(imageUrlString)
//            val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//            MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
//        } else {
//            null // Set imagePart to null if imageUrlString is null
//        }
//
//        // Extract data from map
//        val identification = data["identification"] ?: ""
//        val name = data["name"] ?: ""
//        val fathersName = data["fathers_name"] ?: ""
//        val address = data["address"] ?: ""
//        val religion = data["religion"] ?: ""
//        val maritalStatus = data["marital_status"] ?: ""
//        val mobileNumber = data["mobile_number"] ?: ""
//        val designation = data["designation"] ?: ""
//        val duration = data["duration"] ?: ""
//        val routeUsed = data["route_used"] ?: ""
//        val placeVisitedLastYear = data["place_visited_last_year"] ?: ""
//        val fName = data["f_name"] ?: ""
//        val fAge = data["f_age"] ?: ""
//        val fGender = data["f_gender"] ?: ""
//        val fRelation = data["f_relation"] ?: ""
//        val fAddharNumber = data["f_addhar_number"] ?: ""
//        val fMobileNumber = data["f_mobile_number"] ?: ""
//
//        // Create request body parts for other fields
//        val identificationPart = identification.toRequestBody("text/plain".toMediaTypeOrNull())
//        val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fathersNamePart = fathersName.toRequestBody("text/plain".toMediaTypeOrNull())
//        val addressPart = address.toRequestBody("text/plain".toMediaTypeOrNull())
//        val religionPart = religion.toRequestBody("text/plain".toMediaTypeOrNull())
//        val maritalStatusPart = maritalStatus.toRequestBody("text/plain".toMediaTypeOrNull())
//        val mobileNumberPart = mobileNumber.toRequestBody("text/plain".toMediaTypeOrNull())
//        val designationPart = designation.toRequestBody("text/plain".toMediaTypeOrNull())
//        val durationPart = duration.toRequestBody("text/plain".toMediaTypeOrNull())
//        val routeUsedPart = routeUsed.toRequestBody("text/plain".toMediaTypeOrNull())
//        val placeVisitedLastYearPart = placeVisitedLastYear.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fNamePart = fName.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fAgePart = fAge.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fGenderPart = fGender.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fRelationPart = fRelation.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fAddharNumberPart = fAddharNumber.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fMobileNumberPart = fMobileNumber.toRequestBody("text/plain".toMediaTypeOrNull())
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                // Make the Retrofit request with image and other data
//                val response = imagePart?.let {
//                    service.syncService(
//                        imagePart,
//                        identificationPart,
//                        namePart,
//                        fathersNamePart,
//                        addressPart,
//                        religionPart,
//                        maritalStatusPart,
//                        mobileNumberPart,
//                        designationPart,
//                        durationPart,
//                        routeUsedPart,
//                        placeVisitedLastYearPart,
//                        fNamePart,
//                        fAgePart,
//                        fGenderPart,
//                        fRelationPart,
//                        fAddharNumberPart,
//                        fMobileNumberPart
//                    )
//                }
//                Log.d("success", response.toString())
//            } catch (e: Exception) {
//                Log.e("error", "Error uploading data: ${e.message}")
//                e.printStackTrace()
//            }
//        }


//    fun onNetworkUnavailable() {
//
//    }
//
//}



//    fun onNetworkUnavailable() {
//        var networkState = "false"
//        // Do something when network is unavailable
//    }
//    private fun syncData() {
//        networkState = "true"
//        val db = dbHelper.readableDatabase
//
//        val sqlQuery = "SELECT * FROM users"
//        val cursor: Cursor = db.rawQuery(sqlQuery, null)
//
//        if (cursor.moveToFirst()) { // Move cursor to the first row
//            val dataList = mutableListOf<Map<String, String>>()
//
//            do {
//                val rowData = mutableMapOf<String, String>()
//                for (i in 0 until cursor.columnCount) {
//                    val columnName = cursor.getColumnName(i)
//                    val columnValue = cursor.getString(i)
//                    rowData[columnName] = columnValue
//                }
//                dataList.add(rowData)
//            } while (cursor.moveToNext())
//
//            cursor.close()
//            db.close()
//
//            // Step 2: Check if the fetched data exists in the remote database via API
//            for (data in dataList) {
//                checkDataInRemoteDatabase(data)
//            }
//        } else {
//            // Handle the case when the cursor is empty
//            cursor.close()
//            db.close()
//        }
//    }


//    private fun sendData(data: Map<String, String>) {
//        val fileDir = applicationContext.filesDir
//        val file = File(fileDir, "image.png")
//        val imageUriString = data["image_address"] ?: ""
//        val imageUri = Uri.parse(imageUriString)
//        val inputStream = contentResolver.openInputStream(imageUri)
//        val outputStream = FileOutputStream(file)
//        inputStream?.copyTo(outputStream)
//
//        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
//        val part = MultipartBody.Part.createFormData("image", file.name, requestBody)
//
//        val identification = data["identification"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val name = data["name"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fathersName = data["fathersName"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val address = data["address"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val religion = data["religion"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val maritalStatus = data["maritalStatus"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val mobileNumber = data["mobileNumber"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val designation = data["designation"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val duration = data["duration"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val routeUsed = data["routeUsed"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val placeVisitedLastYear = data["placeVisitedLastYear"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fName = data["fName"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fAge = data["fAge"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fGender = data["fGender"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fRelation = data["fRelation"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fAddharNumber = data["fAddharNumber"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//        val fMobileNumber = data["fMobileNumber"]?.toRequestBody("text/plain".toMediaTypeOrNull())
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.sohangeducation.in/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(syncService::class.java)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val response = retrofit.syncService(
//                    identification ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    name ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    fathersName ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    address ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    religion ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    maritalStatus ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    mobileNumber ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    designation ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    duration ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    routeUsed ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    placeVisitedLastYear ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    fName ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    fAge ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    fGender ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    fRelation ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    fAddharNumber ?: "".toRequestBody("text/plain".toMediaTypeOrNull()),
//                    fMobileNumber ?: "".toRequestBody("text/plain".toMediaTypeOrNull())
//                )
//
//                Log.d("success", response.toString())
//
//            } catch (e: Exception) {
//                Log.e("error", "Error uploading image: ${e.message}")
//                e.printStackTrace()
//                runOnUiThread {
//                    Log.e("Upload Error", "Failed to post data online", e)
//                }
//            }
//        }
//    }


//        try {
//            syncData()
//        }catch(_:Exception){
//
//        }
//        if(intent.getStringExtra("msg") == "do"){
//            try {
//                syncData()
//                Log.d("yes internet message"," this is internet on ")
//            }catch (e : Exception){
//                Log.d("No internet message","$e")
//            }
//        }