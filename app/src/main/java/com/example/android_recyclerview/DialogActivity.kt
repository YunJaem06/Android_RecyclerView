package com.example.android_recyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_recyclerview.databinding.ActivityDialogBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DialogActivity: AppCompatActivity() {

    private lateinit var binding : ActivityDialogBinding
    var alarmList = arrayListOf<ItemAlarm>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}