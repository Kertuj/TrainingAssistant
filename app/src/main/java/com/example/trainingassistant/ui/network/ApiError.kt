package com.example.trainingassistant.ui.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

class ApiError(error: Throwable){

    private val TAG = "ApiError"
    var msg = "An unexpected error occurred"

    init{
        when (error) {
            is HttpException -> {
                val errBodyStr = error.response()?.errorBody()?.string()
                errBodyStr?.let {
                    try {
                        processNonNullJsonStr(JsonParser().parse(it).asJsonObject)
                    } catch (e: Exception) {
                        Log.e(TAG, "Exception $e")
                    }
                }
            }
            is SocketTimeoutException -> this.msg = "The server is under maintenance"
            is UnknownHostException -> this.msg = "No Internet connection"
            else -> Log.e("ApiError", error.toString())

        }
    }

    private fun processNonNullJsonStr(json: JsonObject) {
        if(json.has("errors")) {
            val map = Gson().fromJson(json.asJsonObject["errors"], TreeMap::class.java)
            if(map.isNotEmpty()){
                this.msg = map.firstEntry().value.toString()
                for(entry in map.entries) Log.d(TAG, "${entry.key} : ${entry.value}")
            } else Log.w(TAG, "errors map is empty")
        } else Log.w(TAG, "\"errors\" not found in Json")
    }
}