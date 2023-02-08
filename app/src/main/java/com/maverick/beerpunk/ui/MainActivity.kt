package com.maverick.beerpunk.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverick.beerpunk.R
import com.maverick.beerpunk.adapter.MainAdapter
import com.maverick.beerpunk.model.BeerModelItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var beerList: List<BeerModelItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerView = findViewById(R.id.rv_beer)
        beerList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MainAdapter(this, beerList, object : MainAdapter.BeerClickedListener {
            override fun onBeerClickedListener(position: Int) {
                val bundle = Bundle()
                bundle.putStringArrayList(
                    "food",
                    beerList[position].food_pairing as java.util.ArrayList<String>
                )
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("image", beerList[position].image_url)
                intent.putExtra("name", beerList[position].name)
                intent.putExtra("description", beerList[position].description)
                intent.putExtra("tip", beerList[position].brewers_tips)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        getData()
    }

    private fun getData() {
        viewModel.getAllBeers()
        viewModel.getBeerObservable.observe(this) {
            Log.d("called", it.toString())
            beerList = it
            adapter.beerList = beerList
            adapter.notifyDataSetChanged()
        }
    }
}