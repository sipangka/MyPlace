package com.assignment.myplace.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

//import android.app.Fragment
//import android.app.FragmentManager
//import android.app.FragmentStatePagerAdapter

//import android.app.FragmentStatePagerAdapter


open class BasePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return null
    }

    override fun getCount(): Int {
        return 0
    }
}
