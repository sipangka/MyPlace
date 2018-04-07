package com.assignment.myplace.adapters


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.assignment.myplace.base.BasePagerAdapter
import com.assignment.myplace.fragments.favorite.FavoriteFragment
import com.assignment.myplace.fragments.nearby.NearByFragment

class ViewPagerAdapter
(fm: FragmentManager, internal var Titles: Array<CharSequence>
 , internal var NumbOfTabs: Int
) : BasePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        return if (position == 0) {
            NearByFragment() as Fragment
        } else {
            FavoriteFragment() as Fragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return Titles[position]
    }

    override fun getCount(): Int {
        return NumbOfTabs
    }

}
