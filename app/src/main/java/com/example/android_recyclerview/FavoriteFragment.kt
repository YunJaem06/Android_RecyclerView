package com.example.android_recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_recyclerview.databinding.FragmentAlarmBinding
import com.example.android_recyclerview.databinding.FragmentFavoriteBinding

class FavoriteFragment: Fragment() {

    private lateinit var binding : FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
}