package com.example.smartcity.view.me

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.FragmentMeBinding
import com.example.smartcity.logic.bean.UserBean
import com.example.smartcity.logic.global.GlobalUserDataHolder
import com.example.smartcity.logic.network.OkHttpUtils
import com.google.gson.Gson

class MeFragment : Fragment() {
    private lateinit var binding: FragmentMeBinding
    private lateinit var userData: UserBean.UserData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(layoutInflater)

        return binding.root
//        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onResume() {
        super.onResume()
        // 个人中心
        binding.btnPersonalCenter.setOnClickListener {
            if (!GlobalUserDataHolder.isLogin) {
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(context, PersonalInformationActivity::class.java)
            startActivity(intent)
        }
        // 订单列表
        binding.btnOrderList.setOnClickListener {

        }
        // 修改密码
        binding.btnChangePassword.setOnClickListener {
            if (!GlobalUserDataHolder.isLogin) {
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }
        // 意见反馈
        binding.btnFeedback.setOnClickListener {
            if (!GlobalUserDataHolder.isLogin) {
                Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(context, FeedbackActivity::class.java)
            startActivity(intent)
        }
        // 登录
        binding.btnLoging.setOnClickListener {

        }

        if (!GlobalUserDataHolder.isLogin) {
            Toast.makeText(context, "您还未登录", Toast.LENGTH_SHORT).show()
            binding.imgUserAvatar.setImageDrawable(null)
            binding.txtUserName.text = "未登录"
            return
        }
        OkHttpUtils.getJsonByUrl(
            ApiConstants.GET_USER_DATA_API,
            GlobalUserDataHolder.loginToken
        ) { json ->
            userData = Gson().fromJson(json, UserBean::class.java).userData
            activity?.runOnUiThread {
                Glide.with(binding.imgUserAvatar)
                    .load(ApiConstants.BASE_URL + "/prod-api" + userData.avatarUrl)
                    .into(binding.imgUserAvatar)
                binding.txtUserName.text = userData.nickName
            }
        }
    }
}
