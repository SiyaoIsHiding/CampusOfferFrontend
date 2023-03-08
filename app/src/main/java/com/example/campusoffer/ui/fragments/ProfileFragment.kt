package com.example.campusoffer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.campusoffer.R
import com.example.campusoffer.util.Constants.Companion.CATEGORY_ROOT_ID
import com.example.campusoffer.util.Constants.Companion.QUERY_CATEGORY_ID
import com.example.campusoffer.util.NetworkResult
import com.example.campusoffer.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.view.*


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var mView: View
    private val TAG = "Profile Fragment"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)

    }



//
//    private fun Any.addOnFailureListener(profileFragment: ProfileFragment, any: Any) {
//        Log.v(TAG, "failure listener")
//    }
//
//    private fun <TResult> Task<TResult>.addOnSuccessListener(
//        profileFragment: ProfileFragment,
//        any: Any
//    ): Any {
//        Log.v(TAG, "success listener")
//        return any
//    }


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

//region get product by id
//        val queryMap = HashMap<String, String>()
//        queryMap.put(QUERY_ID, PRODUCT_TEST_ID)
//        profileViewModel.getProductByID(queryMap)
//
//        profileViewModel.productByIDRes.observe(viewLifecycleOwner, { response ->
//            when(response){
//                is NetworkResult.Success -> {
//                    response.data?.let { mView.textView2.text = it.description}
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

//region get subcategory
//        val queryMap = HashMap<String, String>()
//        queryMap.put(QUERY_ID, CATEGORY_ROOT_ID)
//
//        profileViewModel.getSubCategory(queryMap)
//
//
//        profileViewModel.subCategoryRes.observe(viewLifecycleOwner, { response ->
//            when(response){
//                is NetworkResult.Success -> {
//                    response.data?.let { mView.textView2.text = it.subcategory[0].toString() + it.subcategory[1].toString()}
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

//region get user by id
//        val queryMap = HashMap<String, String>()
//        queryMap.put(QUERY_ID, USER_TEST_ID)
//
//        profileViewModel.getUserByID(queryMap)
//
//
//        profileViewModel.userRes.observe(viewLifecycleOwner, { response ->
//            when(response){
//                is NetworkResult.Success -> {
//                    response.data?.let { mView.textView2.text = it.firstName}
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

//region get category by id
//        val queryMap = HashMap<String, String>()
//        queryMap.put(QUERY_ID, CATEGORY_ROOT_ID)
//
//        profileViewModel.getCategory(queryMap)
//
//
//        profileViewModel.categoryRes.observe(viewLifecycleOwner, { response ->
//            when(response){
//                is NetworkResult.Success -> {
//                    response.data?.let { mView.textView2.text = it.name}
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

        return mView
    }

}