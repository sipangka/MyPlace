package com.assignment.myplace.utils

import com.assignment.myplace.persistence.Place
import java.util.ArrayList
import java.util.HashSet

class ArrayUtils{

    companion object {
        @JvmStatic
        fun <T> union(list1: List<T>, list2: List<T>): List<T> {
            val set = HashSet<T>()

            set.addAll(list1)
            set.addAll(list2)

            return ArrayList(set)
        }

        @JvmStatic
        fun  merge(list1: List<Place>, list2: List<Place>): List<Place> {

            list1.forEach {
                it.favorite = false
            }

            list2.forEach {
                var place = it
                list1.forEach {
                    if(it.placeId.equals(place.placeId)){
                        it.favorite = true
                    }
                }
            }
            return list1
        }
    }
}
