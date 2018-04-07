package com.assignment.myplace.fragments.nearby


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.assignment.myplace.R
import com.assignment.myplace.adapters.PlacesRecyclerViewAdapter
import com.assignment.myplace.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_nearby.*
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.assignment.myplace.activities.map.MapActivity
import com.assignment.myplace.persistence.Injection
import com.assignment.myplace.persistence.Place
import com.assignment.myplace.persistence.PlaceViewModel
import com.assignment.myplace.persistence.ViewModelFactory
//import com.assignment.myplace.activities.map.MapActivity.Companion.LOCATION_KEY
import com.google.android.gms.maps.model.LatLng


class NearByFragment : BaseFragment(), INearByFragment.View , PlacesRecyclerViewAdapter.OnItemClickListener{

    val TAG = NearByFragment::class.java.simpleName

    val REQUEST_CODE_LOCATION = 1

    var mPresenter : INearByFragment.Presenter? = null
    var mAdapter : PlacesRecyclerViewAdapter? = null
    lateinit var linearLayoutManager: LinearLayoutManager

    protected override fun getLayout(): Int {
        return R.layout.fragment_nearby
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = NearByFragmentPresenter(this)

        linearLayoutManager = LinearLayoutManager(activity)
        placeRecyclerView.layoutManager = linearLayoutManager
        mAdapter = PlacesRecyclerViewAdapter(activity, this)
        placeRecyclerView.adapter = mAdapter;

        mPresenter?.onViewCreated()

        fab.setOnClickListener(View.OnClickListener { gotoMap(null)})

    }

    override fun onResume() {
        mPresenter?.onViewResume()
        super.onResume()
    }

    override fun onDestroy() {
        mPresenter?.onViewDestroy()
        super.onDestroy()
    }

    override fun getContext(): Context {
        return activity as Context
    }

    override fun gotoMap(location: LatLng?) {

        val intent = Intent(activity, MapActivity::class.java)
        location?.let {
            intent.putExtra(MapActivity.LOCATION_KEY, location)
        }
        startActivityForResult(intent, REQUEST_CODE_LOCATION)
    }

    override fun setData(places: List<Place>) {
        mAdapter?.setData(places)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_LOCATION) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {

                mPresenter?.onActivityResult(requestCode, resultCode, data)

            }
        }
    }


    override fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(getActivity() as Activity)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id -> startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)) })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert = builder.create()
        alert.show()
    }


    /**
     * Implement PlacesRecyclerViewAdapter.OnItemClickListener
     */
    override fun onItemClick(item: Place?) {
        item.let {
            gotoMap(LatLng(it?.location?.latitude!!,  it?.location?.longitude!!))
        }
    }

    override fun onFavoriteClick(item: Place?, isChecked: Boolean) {
        if(isChecked){
            item?.favorite = isChecked
            mPresenter?.addFavoritPlace(place = item!!)
        }else{
            mPresenter?.removeFavoritPlace(place = item!!)
        }
    }
}
