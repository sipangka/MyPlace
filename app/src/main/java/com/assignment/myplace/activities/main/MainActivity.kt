package com.assignment.myplace.activities.main

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.support.v4.view.ViewPager
import com.assignment.myplace.R
import com.assignment.myplace.base.BaseActivity
import com.assignment.myplace.adapters.ViewPagerAdapter
import com.assignment.myplace.utils.Alert
import com.assignment.myplace.utils.PermissionUtils
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : BaseActivity(), IMainActivity.View {

    val TAG = MainActivity::class.java.simpleName

    var mPresenter: IMainActivity.Presenter? = null
    var adapter: ViewPagerAdapter? = null
    var titles = arrayOf<CharSequence>("NEARBY", "FAVORITE")
    var numOfTabs = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar?)

        mPresenter = MainActivityPresenter(this)
        mPresenter?.viewOnCreate()

        requestPermissions()
    }

    fun initInstance() {
        adapter = ViewPagerAdapter(supportFragmentManager, titles, numOfTabs)
        viewPager?.setAdapter(adapter)


        tabs.addTab(tabs.newTab().setText(titles[0]));
        tabs.addTab(tabs.newTab().setText(titles[1]));
        tabs?.setTabGravity(TabLayout.GRAVITY_FILL);

        tabs?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PermissionUtils.REQUEST_CODE_ASK_PERMISSIONS) {

            if (PermissionUtils.onRequestPermissionsResultPass(requestCode, permissions, grantResults, this)) {
                initInstance()
            }else{
                showAlertPermissionDenied()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun requestPermissions() {
        val handler = Handler()
        handler.postDelayed({
            if (PermissionUtils.requestPermissions(this)) {
                initInstance()
            }
        }, 1000)
    }

    fun showAlertPermissionDenied() {
        Alert.showMessage(this, R.string.dialog_permission_denied_title, R.string.dialog_permission_denied_desc, DialogInterface.OnClickListener { dialogInterface, i -> this@MainActivity.finishAffinity() })
    }

    override fun getContext(): Context {
        return this
    }

    override fun onDestroy() {
        mPresenter?.viewOnDestroy()
        super.onDestroy()

    }
}
