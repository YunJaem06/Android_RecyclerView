package com.example.android_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_recyclerview.databinding.FragmentSleepBinding

class SleepFragment: Fragment() {

    private lateinit var binding : FragmentSleepBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSleepBinding.inflate(inflater, container, false)
        return binding.root
    }
}