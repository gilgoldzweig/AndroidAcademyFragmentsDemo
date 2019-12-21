package com.android.academy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SamplePagerAdapter(manager: FragmentManager, val fragments: List<Fragment>) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment =
        fragments[position]

    override fun getCount(): Int =
        fragments.size
}
