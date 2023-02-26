package com.example.campusoffer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.campusoffer.R
import com.example.campusoffer.util.Constants.Companion.CATEGORY_ROOT_ID
import com.example.campusoffer.util.Constants.Companion.PRODUCT_TEST_ID
import com.example.campusoffer.util.Constants.Companion.QUERY_CATEGORY_ID
import com.example.campusoffer.util.Constants.Companion.QUERY_ID
import com.example.campusoffer.util.NetworkResult
import com.example.campusoffer.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.view.*


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false)

        //region get products under category
//        val queryMap = HashMap<String, String>()
//        queryMap.put(QUERY_CATEGORY_ID, CATEGORY_ROOT_ID)
//        profileViewModel.getProductsUnderCategory(queryMap)
//
//        profileViewModel.productsUnderCategoryRes.observe(viewLifecycleOwner, { response ->
//            when(response){
//                is NetworkResult.Success -> {
//                    response.data?.let { mView.textView2.text = it.productId.toString() }
//                }
//                is NetworkResult.Error -> {
//                    Toast.makeText(
//                        requireContext(),
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                is NetworkResult.Loading -> {
//                    Toast.makeText(
//                        requireContext(),
//                        response.message.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        })
        //endregion

        val queryMap = HashMap<String, String>()
        queryMap.put(QUERY_ID, PRODUCT_TEST_ID)
        profileViewModel.getProductByID(queryMap)

        profileViewModel.productByIDRes.observe(viewLifecycleOwner, { response ->
            when(response){
                is NetworkResult.Success -> {
                    response.data?.let { mView.textView2.text = it.description}
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        return mView
    }

}