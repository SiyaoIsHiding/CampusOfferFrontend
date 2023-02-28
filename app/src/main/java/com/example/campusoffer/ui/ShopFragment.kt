package com.example.campusoffer.ui
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.databinding.FragmentShopBinding

/**
 * Reference
 * https://developer.android.com/topic/libraries/view-binding
 */

class ShopFragment : Fragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val view = binding.root
        _binding!!.shimmerRecyclerView.showShimmer()

        binding.productsFilter.setOnClickListener {
            findNavController().navigate(R.id.action_shopFragment_to_filterBottomSheet)
        }

            return view
    }
}

