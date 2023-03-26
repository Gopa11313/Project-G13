package net.gopaltech.project_g13.utils

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import net.gopaltech.project_g13.R
import net.gopaltech.project_g13.utils.Constants.IS_FIRST_TIME
import net.gopaltech.project_g13.utils.Constants.LESSION_COMPLETED_COUNT
import net.gopaltech.project_g13.utils.Constants.LESSON_COUNT
import net.gopaltech.project_g13.utils.Constants.NOTES
import net.gopaltech.project_g13.utils.Constants.USER_NAME

class SharedPref(context: Context) {
    val sharedPreference = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    var editor = sharedPreference.edit()

    fun setName(name: String) {
        editor.putString(USER_NAME, name)
        editor.commit()
    }

    fun getName(): String? {
        return sharedPreference.getString(USER_NAME, null)
    }

    fun setLessonCompletedCount(count: Int) {
        editor.putInt(LESSION_COMPLETED_COUNT, count)
        editor.commit()
    }

    fun getLessonCompletedCount(): Int {
        return sharedPreference.getInt(LESSION_COMPLETED_COUNT, 0)
    }

    fun setIsFirstTime(isFirstTime: Boolean) {
        editor.putBoolean(IS_FIRST_TIME, isFirstTime)
        editor.commit()
    }

    fun getIsFirstTime(): Boolean {
        return sharedPreference.getBoolean(IS_FIRST_TIME, true)
    }

    fun setTotalLessonCount(int: Int) {
        editor.putInt(LESSON_COUNT, int)
        editor.commit()
    }

    fun getTotalLessonCount(): Int {
        return sharedPreference.getInt(LESSON_COUNT, 0)
    }

    fun setCompletedCount(data: Int) {
        editor.putInt(LESSION_COMPLETED_COUNT, data)
        editor.commit()
    }

    fun getCompletedCount(): Int {
        return sharedPreference.getInt(LESSION_COMPLETED_COUNT, 0)
    }

    fun setNotes(data: String) {
        editor.putString(NOTES, data)
        editor.commit()
    }

    fun getNotes(): String?{
        return sharedPreference.getString(NOTES,null )
    }
    fun clear(){
        editor.clear()
    }
}