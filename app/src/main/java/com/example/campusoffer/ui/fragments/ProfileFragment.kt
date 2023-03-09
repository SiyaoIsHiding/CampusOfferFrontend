package com.example.campusoffer.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.databinding.FragmentOverviewBinding
import com.example.campusoffer.databinding.FragmentProfileBinding
import com.example.campusoffer.models.User
import com.example.campusoffer.util.Constants
import com.example.campusoffer.util.Constants.Companion.CATEGORY_ROOT_ID
import com.example.campusoffer.util.Constants.Companion.QUERY_CATEGORY_ID
import com.example.campusoffer.util.NetworkResult
import com.example.campusoffer.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_overview.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var mView: View
    private val TAG = "Profile Fragment"

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


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
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        mView = binding.root


        binding.toggleEditButton.bind(
            binding.profileFirstName,
            binding.profileLastName,
            binding.profileBio
        )

        binding.toggleEditButton.setOnClickListener {
            if (!binding.toggleEditButton.getEditing()) {
                profileViewModel.currentUser.value!!.firstName = binding.profileFirstName.getText()
                profileViewModel.currentUser.value!!.lastName = binding.profileLastName.getText()
                profileViewModel.currentUser.value!!.bio = binding.profileBio.getText()
                profileViewModel.updateProfile(profileViewModel.currentUser.value!!)
//                Log.d("new user info", user.toString())
            }
        }

        requestData()

        return mView
    }

    private fun requestData(){
        val queryMap = HashMap<String, String>()
        queryMap.put(Constants.QUERY_ID, Constants.USER_TEST_ID)
        profileViewModel.getUserProfile(queryMap)
        profileViewModel.currentUser.observe(viewLifecycleOwner){ user ->
            when(user){
                checkNotNull(user) -> {
                    mView.profile_first_name.setText(user.firstName)
                    mView.profile_last_name.setText(user.lastName)
                    mView.profile_bio.setText(user.bio)
                }
                // TODO: create new user
            }

        }
    }


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


}