package com.example.campusoffer.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.campusoffer.R
import com.example.campusoffer.adapters.SellsAdapter
import com.example.campusoffer.databinding.FragmentSellBinding
import com.example.campusoffer.util.NetworkResult
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
    private val SELECT_PICTURE = 200

    private val imageUriList: MutableList<Uri> = mutableListOf()

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

        // button listeners
        binding.submitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.v(TAG, mView.editTitle.text.toString())
                sellViewModel.postNewProduct(binding.editTitle.text.toString(), binding.editDescription.text.toString(), binding.editPrice.text.toString().toDouble())
            }
        })

        binding.addImageButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                imageChooser()
            }
        })

        binding.listSaleButton.setOnClickListener {
            findNavController().navigate(R.id.action_sellFragment_to_saleListActivity)
        }

        sellViewModel.postProductRes.observe(viewLifecycleOwner) {response ->
            when(response){
                is NetworkResult.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Post product successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.editTitle.text.clear()
                    binding.editDescription.text.clear()
                    binding.editPrice.text.clear()
                }

                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Error: " +response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return mView
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun imageChooser(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE){
            imageUriList.clear()
            if(data?.clipData == null){
                // Single image
                val imageUri = data!!.data!!
                imageUriList.add(imageUri)
            }else{
                val count = data.clipData!!.itemCount
                for(i in 0 until count){
                    imageUriList.add(data.clipData?.getItemAt(i)!!.uri)
                }
            }
            inflateImages()
        }
    }

    private fun inflateImages(){
        binding.imagesLinearLayout.removeAllViews()

        for(i in imageUriList.indices){
            val imageView = ImageView(requireContext())
            imageView.setImageURI(imageUriList.get(i))
            binding.imagesLinearLayout.addView(imageView)
        }
    }
}