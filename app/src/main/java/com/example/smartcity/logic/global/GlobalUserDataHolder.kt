package com.example.smartcity.logic.global

object GlobalUserDataHolder {
    var loginToken: String = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImVmYWNmMDVhLWU5YTUtNGIzNC04MzM1LTJkYTNmNzFkMjM0YSJ9.r_xtyNV9IZAFU7QVjgzeVyuv1x0oj1N2pPMKU0VQE4vch2LJcrTpg6CrQtkw9vzWn1JM2r2chpe0Joes7iqg6g"
        val isLogin get() = loginToken.isNotEmpty()
}
