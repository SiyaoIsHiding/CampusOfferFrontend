package com.example.campusoffer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.campusoffer.MyApplication
import com.example.campusoffer.R
import com.example.campusoffer.data.ProductRepository
import com.example.campusoffer.util.Constants.Companion.CATEGORY_ROOT_ID
import com.example.campusoffer.util.Constants.Companion.QUERY_CATEGORY_ID
import com.example.campusoffer.util.NetworkResult
import com.example.campusoffer.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.coroutines.launch
import javax.inject.Inject


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

        val queryMap = HashMap<String, String>()
        queryMap.put(QUERY_CATEGORY_ID, CATEGORY_ROOT_ID)
        profileViewModel.getProductUnderCategory(queryMap)

        profileViewModel.response.observe(viewLifecycleOwner, {response ->
            when(response){
                is NetworkResult.Success -> {
                    response.data?.let { mView.textView2.text = it.productId.toString() }
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