package com.assignment.myplace.fragments.favorite


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.assignment.myplace.R
import com.assignment.myplace.activities.map.MapActivity
import com.assignment.myplace.adapters.PlacesRecyclerViewAdapter
import com.assignment.myplace.base.BaseFragment
import com.assignment.myplace.fragments.nearby.NearByFragmentPresenter
import com.assignment.myplace.persistence.Place
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_nearby.*

class FavoriteFragment : BaseFragment() , IFavoriteFragment.View , PlacesRecyclerViewAdapter.OnItemClickListener{

    val TAG = FavoriteFragment::class.java.simpleName

    var mPresenter : IFavoriteFragment.Presenter? = null
    var mAdapter : PlacesRecyclerViewAdapter? = null
    lateinit var linearLayoutManager: LinearLayoutManager

    protected override fun getLayout(): Int {
        return R.layout.fragment_favorite
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserVisibleHint(false);
        mPresenter = FavoriteFragmentPresenter(this)

        linearLayoutManager = LinearLayoutManager(activity)
        placeRecyclerView.layoutManager = linearLayoutManager
        mAdapter = PlacesRecyclerViewAdapter(activity, this)
        placeRecyclerView.adapter = mAdapter;

        mPresenter?.onViewCreated()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            mPresenter?.getFavoritPlaces()
        } else {

        }
    }

    override fun onDestroy() {
        mPresenter?.onViewDestroy()
        super.onDestroy()
    }

    override fun onResume() {
        //mPresenter?.getFavoritPlaces()
        super.onResume()
    }

    override fun setData(places: List<Place>) {
        mAdapter?.setData(places)
    }

    override fun gotoMap(location: LatLng?) {

    }

    override fun getContext(): Context {
        return activity as Context
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
        if(!isChecked){
            item?.let { mPresenter?.removeFavoritePlace(it) }
        }
    }
}
