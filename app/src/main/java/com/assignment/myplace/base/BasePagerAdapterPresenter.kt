package com.assignment.myplace.base

import android.support.v4.app.Fragment


interface BasePagerAdapterPresenter {

    val count: Int

    fun getTabTitle(position: Int): CharSequence

    fun getItem(position: Int): Fragment

}
