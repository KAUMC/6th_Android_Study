package com.example.flo

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    //소괄호: 클래스 다ㅡㄹ ㄴ클래스로 상속을 진행할 떄는 소괄호를 넣어줘야 한다.

    //지역 변수
    lateinit var binding : ActivitySongBinding
    lateinit var song: Song
    lateinit var timer:Timer

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)



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

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }
    private fun initSong(){
        if(intent.hasExtra("title")&&intent.hasExtra("singer")){
            song=Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second",0),
                intent.getIntExtra("playTime",0),
                intent.getBooleanExtra("isPlaying",false)

            )
        }
        startTimer()
    }

    private fun setPlayer(song:Song){
        binding.songMusicTitleTv.text = intent.getStringExtra("title")!!
        binding.songSingerNameTv.text = intent.getStringExtra("singer")!!

        binding.songStartTimeTv.text = String.format("%02d:%02d",song.second /60,song.second/60)
        binding.songStartTimeTv.text = String.format("%02d:%02d",song.playTime /60,song.playTime/60)
        binding.songProgressSb.progress = (song.second *1000/song.playTime)

        setPlayerStatus(song.isPlaying)

    }

    private fun setPlayerStatus(isPlaying : Boolean){
        song.isPlaying =isPlaying
        timer.isPlaying = isPlaying

        if(isPlaying) {
            binding.songMiniplayerIv.visibility = View.VISIBLE //미니 플레이어 재생 버튼 보이게
            binding.songPauseIv.visibility = View.GONE // 미니 플레이어 중지 버튼 공간과 모양이 안보이게
        }
        else{
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE

        }
    }

    private fun startTimer(){
        timer = Timer(song.playTime,song.isPlaying)
        timer.start()
    }


    inner class Timer(private val playTime: Int, var isPlaying: Boolean = true): Thread(){

        private var second: Int = 0
        private  var mills : Float = 0f

        override fun run() {
            super.run()
            try {
                while(true){
                    if(second >= playTime)
                    {
                        break
                    }
                    if(isPlaying)
                    {
                        sleep(50)
                        mills += 50

                        runOnUiThread{
                            binding.songProgressSb.progress = ((mills/playTime)*100).toInt()
                        }

                        if(mills %1000 == 0f){
                            runOnUiThread{
                                binding.songStartTimeTv.text = String.format("%02d:%02d",second /60,second/60)
                            }
                            second++
                        }

                    }
                }
            }catch(e: InterruptedException){
                Log.d("Song","쓰레드가 죽었습니다. ${e.message}")
            }
        }
    }
    //사용자가 포커스를 잃었을 떄 음악이 중지
    override fun onPause(){
        super.onPause()
        setPlayerStatus(false)
        song.second=((binding.songProgressSb.progress*song.playTime)/100)/1000
        val sharedPreferences=getSharedPreferences("song", MODE_PRIVATE)
        val editor = sharedPreferences.edit() //에디터



    }











}