package com.example.campusoffer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.campusoffer.R
import com.example.campusoffer.adapters.PagerAdapter
import com.example.campusoffer.ui.fragments.ImagesFragment
import com.example.campusoffer.ui.fragments.OverviewFragment
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.placeholder_row.*

class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(ImagesFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("More photos")

        val productBundle = Bundle()
        productBundle.putParcelable("productBundle", args.product)

        val mAdapter = PagerAdapter(
            productBundle,
            fragments,
            titles,
            supportFragmentManager
        )
        viewPager.adapter = mAdapter
        tabLayout.setupWithViewPager(viewPager)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}