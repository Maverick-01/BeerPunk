package com.maverick.beerpunk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.maverick.beerpunk.R

class DetailActivity : AppCompatActivity() {
    private lateinit var beerName: TextView
    private lateinit var beerImage: ImageView
    private lateinit var beerDescription: TextView
    private lateinit var beerFoodPairing: TextView
    private lateinit var brewerTips: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        beerName = findViewById(R.id.detail_beer_name)
        beerImage = findViewById(R.id.detail_beer_image)
        beerDescription = findViewById(R.id.detail_beer_description)
        beerFoodPairing = findViewById(R.id.detail_beer_food)
        brewerTips = findViewById(R.id.detail_brewers_tips)

        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val food = intent.getStringArrayListExtra("food")
        val tip = intent.getStringExtra("tip")

        beerName.text = name
        beerDescription.text = description
        brewerTips.text = tip
        if (food != null) {
            for (item in food)
                beerFoodPairing.append("* $item \n")
        }
        Glide.with(this).load(image).into(beerImage)
    }
}