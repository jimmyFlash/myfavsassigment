package com.jimmy.myfavsassigment.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jimmy.myfavsassigment.businesslogic.models.AnimeObj
import com.jimmy.myfavsassigment.databinding.RvItemBinding

class AnimeRecyclerViewAdapter (private var items: ArrayList<AnimeObj>, private var listener: OnItemClickListener):
    RecyclerView.Adapter<AnimeRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): AnimeRecyclerViewAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)

        val binding : RvItemBinding = RvItemBinding.inflate(layoutInflater, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: AnimeRecyclerViewAdapter.ViewHolder, p1: Int) {
        p0.bind(items[p1], listener)
    }


    fun replaceData(arrayList: ArrayList<AnimeObj>) {
        items = arrayList
        notifyDataSetChanged()
    }



    // interface for click handeling
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


    class ViewHolder(private var binding: RvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(ani: AnimeObj, listener: OnItemClickListener?) {
            binding.anime = ani

            if (listener != null) {
                // can replace parameter with _ if you don’t use it
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }
            binding.executePendingBindings()
        }
    }
}