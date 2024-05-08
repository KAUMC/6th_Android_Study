package com.example.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flo.databinding.FragmentBannerBinding

class BannerFragment(val imgRes : Int) : Fragment() {
    lateinit var binding : FragmentBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBannerBinding.inflate(inflater, container, false)

        binding.bannerImageIv.setImageResource(imgRes)
        return binding.root
    }


}