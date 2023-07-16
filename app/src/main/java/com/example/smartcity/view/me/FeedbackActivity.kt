package com.example.smartcity.view.me

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smartcity.constants.ApiConstants
import com.example.smartcity.databinding.ActivityFeedbackBinding
import com.example.smartcity.logic.global.GlobalUserDataHolder
import com.example.smartcity.logic.network.OkHttpUtils
import kotlinx.android.synthetic.main.activity_feedback.*

class FeedbackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)

        binding.btnBack.setOnClickListener { finish() }
        binding.btnSubmit.setOnClickListener {
            if (editFeedback.text.toString().isEmpty()) {
                Toast.makeText(this, "内容不能为空!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val json = "{" +
                    "  \"content\": \"<实际的反馈文本>\"," +
                    "  \"title\": \"意见反馈\"\n" +
                    "}"
            OkHttpUtils.postRequest(
                ApiConstants.POST_FEEDBACK_API,
                json,
                GlobalUserDataHolder.loginToken
            ) {
            }
        }

        setContentView(binding.root)
    }
}