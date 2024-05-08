package com.example.flo

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    lateinit var binding : ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.songDownIb.setOnClickListener{
            finish()
        }
        binding.songMiniplayerIv.setOnClickListener{
            setPlayerStatus(false)
        }
        binding.songPauseIv.setOnClickListener{
            setPlayerStatus(true)
        }
    }
    fun setPlayerStatus(isPlaying : Boolean){
        if(isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE //미니 플레이어 재생 버튼 보이게
            binding.songPauseIv.visibility = View.GONE // 미니 플레이어 중지 버튼 공간과 모양이 안보이게
        }
        else{
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE

        }
    }

}