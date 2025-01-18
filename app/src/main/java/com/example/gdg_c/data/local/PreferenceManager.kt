package com.example.gdg_c.data.local
import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val USER_IDENTITY_KEY = "user_identity"
    }

    // 저장
    fun saveUserIdentity(userIdentity: String) {
        sharedPreferences.edit()
            .putString(USER_IDENTITY_KEY, userIdentity)
            .apply()
    }

    // 읽기
    fun getUserIdentity(): String? {
        return sharedPreferences.getString(USER_IDENTITY_KEY, null)
    }

    // 삭제
    fun clearUserIdentity() {
        sharedPreferences.edit()
            .remove(USER_IDENTITY_KEY)
            .apply()
    }
}
