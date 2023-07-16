package com.example.smartcity.logic.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object OkHttpUtils {
    private val client: OkHttpClient = OkHttpClient()

    fun getJsonByUrl(requestUrl: String, token: String = "", requestCallBack: RequestCallBack) {
        val request = Request.Builder().url(requestUrl).header("Authorization", token).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.run {
                    val jsonData = string()
                    var statusCode = 401
                    try {
                        statusCode = JSONObject(jsonData).optInt("code", 401)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    if (statusCode == 401) {
                        return
                    }
                    requestCallBack.callback(jsonData)
                }
            }
        })
    }

    fun postRequest(
        requestUrl: String,
        requestData: String,
        token: String = "",
        requestCallBack: RequestCallBack
    ) {
        val mediaType = "application/json".toMediaTypeOrNull()
        val requestBody = requestData.toRequestBody(mediaType)

        // 创建 Request 对象时添加 header
        val request =
            Request.Builder().url(requestUrl).header("Authorization", token).post(requestBody)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.run {
                    val jsonData = string()
                    requestCallBack.callback(jsonData)
                }
            }
        })
    }

    fun putRequest(
        requestUrl: String,
        requestData: String,
        token: String = "",
        requestCallBack: RequestCallBack
    ) {
        val mediaType = "application/json".toMediaTypeOrNull()
        val requestBody = requestData.toRequestBody(mediaType)

        // 创建 Request 对象时添加 header
        val request =
            Request.Builder().url(requestUrl).header("Authorization", token).put(requestBody)
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.run {
                    val jsonData = string()
                    requestCallBack.callback(jsonData)
                }
            }
        })
    }


    fun interface RequestCallBack {
        fun callback(jsonData: String)
    }

}
