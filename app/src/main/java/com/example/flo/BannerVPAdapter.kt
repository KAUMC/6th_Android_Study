package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BannerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    // 뷰 페이저에 데이터를 전달할 때 몇개의 데이터를 전달할 것이냐
    override fun getItemCount(): Int = fragmentlist.size

    // 프레그먼트리스트 안에 있는 아이템을 생성해주는 함수
    override fun createFragment(position: Int): Fragment =fragmentlist[position]

    fun addFragment(fragment: Fragment){
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1)
    }
}
