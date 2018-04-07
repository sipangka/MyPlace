package com.assignment.myplace.base


interface BasePresenter {

    fun viewOnCreate()

    fun viewOnStart()

    fun viewOnResume()

    fun viewOnPause()

    fun viewOnStop()

    fun viewOnDestroy()

    fun viewOnCreateView()

    fun viewOnDestroyView()
}
