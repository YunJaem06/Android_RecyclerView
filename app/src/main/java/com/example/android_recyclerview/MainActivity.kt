package com.example.android_recyclerview

import android.animation.ObjectAnimator
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.android_recyclerview.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var isFabOpen = false

    var alarmList = arrayListOf<ItemAlarm>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFABClickEvent()

        setRv()
        getTime(binding.tvAlarmNoSetting)

    }

    private fun setRv() {
        alarmList = loadPre("Alarm")

        val adapter = AlarmRvAdapter(this)
        binding.rvAlarmMainList.adapter = adapter
        adapter.setList(alarmList)
        (binding.rvAlarmMainList.adapter as AlarmRvAdapter).notifyDataSetChanged()
    }

    private fun setFABClickEvent() {
        binding.fabAlarmMain.setOnClickListener {
            toggleFab()
        }
    }

    private fun getTime(textView: TextView) {
        val cal = Calendar.getInstance()

        val timeSet = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            fun day(): String {
                return if (hour > 12) "오후"
                else "오전"
            }

            val time = SimpleDateFormat("HH:mm").format(cal.time)
            textView.text = time
            alarmList.add(ItemAlarm(time, day(), false))
            savePre("key", alarmList)
        }
        binding.fabAlarmAlarm.setOnClickListener {
            TimePickerDialog(
                this, android.app.AlertDialog.THEME_HOLO_LIGHT, timeSet, cal.get(
                    Calendar.HOUR_OF_DAY
                ), cal.get(Calendar.MINUTE), true
            ).show()
        }
    }

    private fun toggleFab() {

        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabAlarmQuick, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmAlarm, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmMain, View.ROTATION, 45f, 0f).apply { start() }

        } else {
            ObjectAnimator.ofFloat(binding.fabAlarmQuick, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmAlarm, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmMain, View.ROTATION, 0f, 45f).apply { start() }
        }
        isFabOpen = !isFabOpen

    }

    private fun savePre(key: String, list: ArrayList<ItemAlarm>) {
        val sp = getSharedPreferences("timeData", MODE_PRIVATE)
        val editor = sp.edit()
        var gson = Gson()
        var json = gson.toJson(list)
        editor.putString("Alarm", json)
        editor.apply()
    }

    private fun loadPre(key: String): ArrayList<ItemAlarm> {
        val sp = getSharedPreferences("timeData", MODE_PRIVATE)
        var gson = Gson()
        var json = sp.getString("Alarm", "")
        val type = object : TypeToken<ArrayList<ItemAlarm>>() {}.type
        return gson.fromJson(json, type)
    }

}