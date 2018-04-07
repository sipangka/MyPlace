package com.assignment.myplace.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


interface BaseRecyclerAdapterPresenter {

    val count: Int

    fun viewHolderIdentification(position: Int): Int

    fun onCreateViewHolderIdentification(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)

}
