package com.example.smartcity.view.me

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ActivityPersonalInformationBinding
import com.example.smartcity.logic.bean.PostUserDataBean
import com.example.smartcity.logic.bean.UserBean
import com.example.smartcity.logic.global.GlobalUserDataHolder
import com.example.smartcity.logic.network.OkHttpUtils
import com.google.gson.Gson

class PersonalInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalInformationBinding
    private lateinit var userData: UserBean.UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        OkHttpUtils.getJsonByUrl(
            ApiConstants.GET_USER_DATA_API,
            GlobalUserDataHolder.loginToken
        ) { json ->
            userData = Gson().fromJson(json, UserBean::class.java).userData
            runOnUiThread {
                Glide.with(binding.imageView)
                    .load(ApiConstants.BASE_URL + "/prod-api" + userData.avatarUrl)
                    .into(binding.imageView)
                when (userData.sex) {
                    0 -> binding.radioGroupSex.check(R.id.radioBtnMan)
                    1 -> binding.radioGroupSex.check(R.id.radioBtnWoman)
                }
                binding.editCardID.setText(userData.idCard)
                binding.editPhone.setText(userData.phoneNumber)
                binding.editUserNickName.setText(userData.nickName)
            }
        }
        binding.btnSubmit.setOnClickListener {
            val sex = when (binding.radioGroupSex.checkedRadioButtonId) {
                R.id.radioBtnMan -> "0"
                R.id.radioBtnWoman -> "1"
                else -> "0"
            }
            OkHttpUtils.putRequest(
                ApiConstants.POST_SET_USER_DATA_API,
                Gson().toJson(
                    PostUserDataBean(
                        binding.editUserNickName.text.toString(),
                        binding.editPhone.text.toString(),
                        sex,
                        binding.editCardID.text.toString()
                    )
                ),
                GlobalUserDataHolder.loginToken
            ) {
                runOnUiThread {
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
