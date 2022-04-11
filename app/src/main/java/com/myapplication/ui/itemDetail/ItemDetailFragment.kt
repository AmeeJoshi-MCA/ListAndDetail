package com.myapplication.ui.itemDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.myapplication.R
import com.myapplication.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment  : Fragment() {

    private val args: ItemDetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.productDetail=args.product
        return binding.root
    }
}