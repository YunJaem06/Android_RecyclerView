package com.example.android_recyclerview

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.android_recyclerview.databinding.FragmentAlarmBinding

class AlarmFragment: Fragment() {

    private lateinit var binding : FragmentAlarmBinding

    private var isFabOpen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFABClickEvent()
    }

    private fun setFABClickEvent() {
        binding.fabAlarmMain.setOnClickListener {
            toggleFab()
        }
    }
    private fun toggleFab() {

        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabAlarmQuick, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmAlarm, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmMain, View.ROTATION,45f, 0f).apply { start() }

        } else {
            ObjectAnimator.ofFloat(binding.fabAlarmQuick, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmAlarm, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabAlarmMain, View.ROTATION,0f, 45f).apply { start() }
        }
        isFabOpen = !isFabOpen
    }
}