package com.samplekotlinprojectsetup.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.example.playstation.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

object CM {

    @SuppressLint("CommitPrefEdits")
    fun setSharedPreference(activity: Context?, key: String, value: Any) {

        val prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity.packageName, Activity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor? = prefs!!.edit()

        when (value) {
            is String -> editor!!.putString(key, value)
            is Boolean -> editor!!.putBoolean(key, value)
            is Int -> editor!!.putInt(key, value)
            is Long -> editor!!.putLong(key, value)
            is Float -> editor!!.putFloat(key, value)
        }

        editor!!.apply()
    }

    fun getSharedPreference(activity: Context?, key: String, defaultValue: Any): Any {

        val prefs = activity?.getSharedPreferences(
            activity.packageName, Activity.MODE_PRIVATE
        )
        return when (defaultValue) {
            is String -> prefs!!.getString(key, defaultValue)!!
            is Boolean -> prefs!!.getBoolean(key, defaultValue)
            is Int -> prefs!!.getInt(key, defaultValue)
            is Long -> prefs!!.getLong(key, defaultValue)
            else -> prefs!!.getFloat(key, defaultValue as Float)
        }
    }

    fun clearSharedPreference(activity: Context?) {

        val prefs: SharedPreferences? = activity!!.getSharedPreferences(
            activity.packageName, Activity.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor? = prefs!!.edit()
        editor?.clear()?.apply()
    }

    fun checkPrivilege(ctx: Context, str: String): Boolean {

        val privileges = getSharedPreference(ctx, Constants.PREF_PRIVILEGE, "") as String

        if (privileges.contains(str, true)) {
            return true
        }

        return false
    }

    @SuppressLint("SimpleDateFormat")
    fun changeDateFormat(
        dateToConvert: String,
        currentDateFormat: String,
        desiredDateFormat: String
    ): String {

        if (dateToConvert.isNotEmpty()) {

            val formatter = SimpleDateFormat(currentDateFormat)
            val date = formatter.parse(dateToConvert) as Date
            val newFormat = SimpleDateFormat(desiredDateFormat)
            return newFormat.format(date)
        }

        return ""
    }

    @SuppressLint("SimpleDateFormat")
    fun getMillisecond(dateTime: String, currentDateFormat: String): Long {
        val sdf = SimpleDateFormat(currentDateFormat)
        val date = sdf.parse(dateTime)
        return date.time
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateTimeFromMilliseconds(millis: Long, desiredDateTimeFormat: String): String {
        val sdf = SimpleDateFormat(desiredDateTimeFormat)
        val date = Date(millis)
        return sdf.format(date)
    }

    @SuppressLint("DefaultLocale")
    fun textWordCapitalization(str: String): String {

        if (str.isEmpty())
            return ""

        val words = str.split(" ").toMutableList()
        var output = ""
        for (word in words) {
            output += word.capitalize() + " "
        }
        output = output.trim()

        return output
    }

   /* fun clearDatabase(mDatabase: AppDatabase) {
        mDatabase.daoUser().deleteAll()
    }*/

    fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun printErrorLog(tag: String, message: String) {
        Log.e(tag, message)
    }

    fun printInfoLog(tag: String, message: String) {
        Log.e(tag, message)
    }

    fun printVerboseLog(tag: String, message: String) {
        Log.e(tag, message)
    }

    fun printDebugLog(tag: String, message: String) {
        Log.e(tag, message)
    }
}