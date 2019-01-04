package com.jimmy.myfavsassigment.ui.views.home

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jimmy.myfavsassigment.R
import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj
import com.jimmy.myfavsassigment.databinding.HomeFragmentBinding
import com.jimmy.myfavsassigment.ui.adapters.AnimeRecyclerViewAdapter
import com.jimmy.myfavsassigment.ui.extensions.errorDialog


class HomeFragment : Fragment(), AnimeRecyclerViewAdapter.OnItemClickListener {

    override fun onItemClick(position: Int, aniOb: AnimeObj?) {

        /* Create an instance of the dialog fragment and show it */
        val dialog = RatingDialogFrag.newInstance()
        val args = Bundle()
        args.putInt("oldRating", aniOb?.numberOfStars!!)
        args.putInt("pos", position)
        dialog.arguments = args
        dialog.show(activity?.supportFragmentManager, "AddListDialogFragment")
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var myHomeFragmentBinding: HomeFragmentBinding

    private lateinit var homeViewModel: HomeViewModel

    private var animeRecyclerViewAdapter: AnimeRecyclerViewAdapter = AnimeRecyclerViewAdapter(arrayListOf(), this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // invoke databinding
        myHomeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.home_fragment,
            container, false )

        myHomeFragmentBinding.animeRv.layoutManager = LinearLayoutManager(activity)
        myHomeFragmentBinding.animeRv.adapter = animeRecyclerViewAdapter


        return myHomeFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // initialise VM
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        myHomeFragmentBinding.viewModel = homeViewModel
        homeViewModel.loadRepositories()

        homeViewModel.repositories.observe(this, Observer {

            it?.let { it1 -> animeRecyclerViewAdapter.replaceData(it1) }
        })

        homeViewModel.error.observe(this, Observer {

            if(it == true)R.string.error.errorDialog(activity as Activity)

        })

        myHomeFragmentBinding.executePendingBindings()
    }


    fun updateItemRating(position: Int, rating: Int){

        Log.e("iohoihohoihoih", "postion $position & rating $rating")
        homeViewModel.refreshRepositories(position, rating)
    }

}
