package com.fitdev.findindonesiatourism.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fitdev.findindonesiatourism.remote.response.gmaps.textsearch.ResultsItem
import com.fitdev.myapplication.databinding.ItemHomePopularBinding

class PopularViewAdapter(private val popularDataList : List<ResultsItem?>?) : RecyclerView.Adapter<PopularViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemHomePopularBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val popularData = popularDataList?.get(position)
        with(holder){
            this.binding.popularName.text = popularData?.name
            this.binding.popularRating.rating = popularData?.rating.toString().toFloat()
            this.binding.popularImage.load(photoUrl(popularData?.photos?.get(0)?.photoReference))
        }
    }

    override fun getItemCount(): Int {
        return popularDataList?.size ?: 0
    }

    private fun photoUrl(photoReference: String?): String{
        val key = "AIzaSyBl416wxXDeyiRk3ZuTsLXFjRhx_1e_QXg"
        val maxheight = "250"
        return if(photoReference.isNullOrEmpty()){
            "https://aplikasijpm.online/fitproject/default/defaultimage.png"
        } else{
            "https://maps.googleapis.com/maps/api/place/photo?key=$key&photo_reference=$photoReference&maxheight=$maxheight"
        }
    }
}