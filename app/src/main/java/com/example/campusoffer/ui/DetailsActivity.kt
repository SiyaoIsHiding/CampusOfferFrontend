package com.example.campusoffer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.campusoffer.R
import com.example.campusoffer.adapters.PagerAdapter
import com.example.campusoffer.ui.fragments.ImagesFragment
import com.example.campusoffer.ui.fragments.OverviewFragment
import com.example.campusoffer.viewmodels.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.placeholder_row.*

class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    // Share Data in MainModel (from local database or API)
    private var favoriteViewModel = FavoriteViewModel()
    private var productSaved = false

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.save_to_favorites_menu)
        checkSavedProducts(menuItem!!)
        return true
    }

    private fun checkSavedProducts( item: MenuItem) {
        for ( currFavorite in favoriteViewModel.getCurrentFavoritesList()) {
            if (currFavorite.id == args.product.id) {
                changeMenuItemColor( item, R.color.yellow)
                productSaved = true
            } else {
                changeMenuItemColor( item, R.color.white)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu){
            Log.d("saved item", item.toString())
            Log.d("item info", item.itemId.toString())
            Log.d("item title", item.title.toString())
            saveToFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites(item: MenuItem) {
        val selectProduct = args.product
        favoriteViewModel.insertFavoriteProduct(selectProduct)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("Favorite Product Saved")
        Log.d("currList", favoriteViewModel.getCurrentFavoritesList().toString())
        Log.d("currListLength", favoriteViewModel.getCurrentFavoritesList().size.toString())
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            detailsLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay"){}
            .show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }
}