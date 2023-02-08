package com.maverick.beerpunk.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverick.beerpunk.R
import com.maverick.beerpunk.model.BeerModelItem

class MainAdapter(
    private val context: Context,
    var beerList: List<BeerModelItem>,
    private val beerClickedListener: BeerClickedListener
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    interface BeerClickedListener {
        fun onBeerClickedListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_beer, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position)
        holder.beerName.text = beerList[position].name
        holder.beerEst.text = "Since: ${beerList[position].first_brewed}"
        holder.beerAbuValue.text = "Alcohol by volume(abv): ${beerList[position].abv}"
        Glide.with(context).load(beerList[position].image_url).into(holder.beerImage)
    }

    override fun getItemCount() = beerList.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerName: TextView = itemView.findViewById(R.id.tv_beer_name)
        val beerAbuValue: TextView = itemView.findViewById(R.id.tv_beer_value)
        val beerImage: ImageView = itemView.findViewById(R.id.iv_beer)
        val beerEst: TextView = itemView.findViewById(R.id.tv_beer_est)
        val beerCard: CardView = itemView.findViewById(R.id.beer_card)
        fun bind(position: Int) {
            beerCard.setOnClickListener {
                beerClickedListener.onBeerClickedListener(position)
            }
        }
    }
}