package com.example.smartcity.constants

class ApiConstants {
    companion object {
        //                const val BASE_URL = "http://124.93.196.45:10001"
        const val BASE_URL = "http://192.168.10.111:10001"

        // 轮播图数据API接口
        const val GET_HOME_BANNER_API = "$BASE_URL/prod-api/api/rotation/list"

        // 全部服务API接口
        const val GET_ALL_SERVICE_API = "$BASE_URL/prod-api/api/service/list"

        // 新闻咨询API接口
        const val GET_NEWS_API = "$BASE_URL/prod-api/press/press/list"

        // 新闻分类API接口
        const val GET_NEWS_TYPE_API = "$BASE_URL/prod-api/press/category/list"

        // 登录API接口
        const val LOGIN_API = "$BASE_URL/prod-api/api/login"

        // 修改密码API接口
        const val PUT_CHANGE_PASSWORD_API = "$BASE_URL/prod-api/api/common/user/resetPwd"

        // 获取用户数据API接口
        const val GET_USER_DATA_API = "$BASE_URL/prod-api/api/common/user/getInfo"

        // 提交意见反馈接口
        const val POST_FEEDBACK_API = "$BASE_URL/prod-api/api/common/feedback"

        // 修改用户信息API接口
        const val POST_SET_USER_DATA_API = "$BASE_URL/prod-api/api/common/user"

        // 查询停车场接口
        const val GET_PARK_LOT_API = "$BASE_URL/prod-api/api/park/lot/list"

        // 查询门诊预约医院轮播图接口
        const val HOSPITAL_BANNER_API = "$BASE_URL/prod-api/api/hospital/banner/list"

        // 查询医院信息列表接口
        const val HOSPITAL_INFO_API = "$BASE_URL/prod-api/api/hospital/hospital/list"

        // 查询当前用户就诊人卡片
        const val GET_PATIENT_CARD_API = "$BASE_URL/prod-api/api/hospital/patient/list"

        // 提交就诊卡信息
        const val POST_GET_PATIENT_CARD_API = "$BASE_URL/prod-api/api/hospital/patient"

        // 查询门诊分类
        const val GET_OUTPATIENT_TYPE_API = "$BASE_URL/prod-api/api/hospital/category/list"

        // 查询房子列表
        const val GET_HOUSE_LIST_API = "$BASE_URL/prod-api/api/house/housing/list"
    }
}
