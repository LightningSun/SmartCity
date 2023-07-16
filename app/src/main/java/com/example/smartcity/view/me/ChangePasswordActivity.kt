package com.example.smartcity.view.me

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ActivityChangePasswordBinding
import com.example.smartcity.logic.bean.MessageBean
import com.example.smartcity.logic.bean.PostChangePasswordBean
import com.example.smartcity.logic.global.GlobalUserDataHolder
import com.example.smartcity.logic.network.OkHttpUtils
import com.google.gson.Gson

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private lateinit var oldPassword: String
    private lateinit var newPassword1: String
    private lateinit var newPassword2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            oldPassword = binding.editOldPassword.text.toString()
            newPassword1 = binding.editNewPassword1.text.toString()
            newPassword2 = binding.editNewPassword2.text.toString()
            if (newPassword1.isEmpty() || newPassword2.isEmpty() || oldPassword.isEmpty()) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (newPassword1 != newPassword2) {
                Toast.makeText(this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (oldPassword == newPassword1) {
                Toast.makeText(this, "新旧密码不能相同", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            OkHttpUtils.putRequest(
                ApiConstants.PUT_CHANGE_PASSWORD_API,
                Gson().toJson(PostChangePasswordBean(newPassword1, oldPassword)),
                GlobalUserDataHolder.loginToken
            ) { json ->
                val responseData = Gson().fromJson(json, MessageBean::class.java)
                if (responseData.responseCode == 500) {
                    runOnUiThread {
                        Toast.makeText(this, responseData.responseMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                    return@putRequest
                }
                runOnUiThread {
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show()
                }
                GlobalUserDataHolder.loginToken = ""
                finish()
            }
        }
    }
}