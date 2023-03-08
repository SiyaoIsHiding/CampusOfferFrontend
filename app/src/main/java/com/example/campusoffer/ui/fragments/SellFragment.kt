package com.example.campusoffer.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.adapters.SellsAdapter
import com.example.campusoffer.databinding.FragmentSellBinding
import com.example.campusoffer.viewmodels.SellViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sell.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SellFragment : Fragment() {

    // This property is only valid between onCreateView and onDestroyView.
    private var _binding: FragmentSellBinding? = null
    private val binding get() = _binding!!
    private lateinit var sellViewModel: SellViewModel
    private val mAdapter by lazy { SellsAdapter() }
    private lateinit var mView: View

    private val TAG = "SellFragment"

    private lateinit var submitButton : Button
    private lateinit var titleField : EditText
    private lateinit var descriptionField : EditText
    private lateinit var priceField : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellViewModel = ViewModelProvider(requireActivity()).get(SellViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSellBinding.inflate(inflater, container, false)
        mView = binding.root

        // init
        submitButton = mView.submitButton
        titleField = mView.editTitle
        descriptionField = mView.editDescription
        priceField = mView.editPrice

        submitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.v(TAG, mView.editTitle.text.toString())
                sellViewModel.postNewProduct(titleField.text.toString(), descriptionField.text.toString(), priceField.text.toString().toDouble())
            }
        })

        binding.listSaleButton.setOnClickListener {
            findNavController().navigate(R.id.action_sellFragment_to_saleListActivity)
        }

        return mView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}