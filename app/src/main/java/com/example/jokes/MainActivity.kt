package com.example.jokes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.SimpleAdapter
import java.util.ArrayList
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    internal var categories = arrayOf(
        "explicit",
        "nerdy"
    )
    internal var flags = intArrayOf(
        R.drawable.explicit,
        R.drawable.nerdy
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aList = ArrayList<HashMap<String, String>>()

        for (i in flags.indices) {
            val hm = HashMap<String, String>()
            hm["txt"] = categories[i]
            hm["flag"] = Integer.toString(flags[i])
            aList.add(hm)
        }
        // Keys used in Hashmap
        val from = arrayOf("flag", "txt")
        // Ids of views in listview_layout
        val to = intArrayOf(R.id.flag, R.id.txt)
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        val adapter = SimpleAdapter(baseContext, aList, R.layout.cell, from, to)
        // Getting a reference to gridview of MainActivity
        val gridView = findViewById(R.id.gridview) as GridView
        // Setting an adapter containing images to the gridview
        gridView.adapter = adapter
        // gridview calling and go to another activity
        gridView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, l ->
            val i = Intent(this@MainActivity, Category::class.java)
            i.putExtra("category", categories[position])
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}

