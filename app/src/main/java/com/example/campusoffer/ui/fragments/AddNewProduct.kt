package com.example.campusoffer.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.adapters.ProductsAdapter
import com.example.campusoffer.databinding.FragmentAddNewProductBinding
import com.example.campusoffer.databinding.FragmentShopBinding
import com.example.campusoffer.viewmodels.ShopViewModel
import kotlinx.android.synthetic.main.activity_details.*


class AddNewProduct : Fragment() {

    private var _binding: FragmentAddNewProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewProductBinding.inflate(inflater, container, false)
        mView = binding.root

        // Enable the up button in the action bar
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        return mView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Log.d("itemId", item.itemId.toString())
                // Handle the up button click event and navigate to the previous screen
                activity?.onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddNewProduct.
         */
         // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddNewProduct().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}