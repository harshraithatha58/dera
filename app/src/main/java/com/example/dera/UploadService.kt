package com.example.dera

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {
    @Multipart
    @POST("/create.php")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("identification") identification: RequestBody,
        @Part("name") name: RequestBody,
        @Part("fathersName") fathersName: RequestBody,
        @Part("address") address: RequestBody,
        @Part("religion") religion: RequestBody,
        @Part("maritalStatus") maritalStatus: RequestBody,
        @Part("mobileNumber") mobileNumber: RequestBody,
        @Part("designation") designation: RequestBody,
        @Part("duration") duration: RequestBody,
        @Part("routeUsed") routeUsed: RequestBody,
        @Part("placeVisitedLastYear") placeVisitedLastYear: RequestBody,
        @Part("fName") fName: RequestBody,
        @Part("fAge") fAge: RequestBody,
        @Part("fGender") fGender: RequestBody,
        @Part("fRelation") fRelation: RequestBody,
        @Part("fAddharNumber") fAddharNumber: RequestBody,
        @Part("fMobileNumber") fMobileNumber: RequestBody
    ): ResponseBody
}
