package com.myapplication.ui.itemList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.myapplication.R
import com.myapplication.databinding.FragmentItemBinding
import com.myapplication.network.model.RestApiResponse
import com.myapplication.util.ContentItem
import com.myapplication.util.DataResult
import com.myapplication.util.RecyclerViewItem
import com.myapplication.util.SectionItem
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemListFragment : Fragment() {
    private val viewModel: ItemListViewModel by viewModels()

    @Inject
    lateinit var adapter: ItemListAdapter
    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_item, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewItems.adapter = adapter
        binding.recyclerViewItems.itemAnimator = null
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getItems()
        getData()

        adapter.clickListener.onItemClick = {
            findNavController().navigate(
                 ItemListFragmentDirections.actionAlbumsListToAlbumDetails(it)
            )
        }
    }

    private fun getData() {
        viewModel.dataResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is DataResult.Empty -> displayItems(message = getString(R.string.no_data_found))
                is DataResult.Error -> displayItems(message = result.message)
                is DataResult.Loading -> binding.progressBarItems.isVisible = true
                is DataResult.Success -> handleData(result.data)
            }
        }
    }

    private fun displayItems(message: String = "") {
        binding.apply {
            progressBarItems.isVisible = false
            recyclerViewItems.isVisible = message.isEmpty()
            tvErrorItems.isVisible = message.isNotEmpty()
            tvErrorItems.text = message
        }
    }

    private fun handleData(data: RestApiResponse) {
        displayItems()
        initRecyclerView(data)
    }

    private fun initRecyclerView(data: RestApiResponse) {
        val dataSet = arrayListOf<RecyclerViewItem>()

        for (apiResponse in data) {
            dataSet.add(SectionItem(apiResponse))
            for(product in apiResponse.products){
                dataSet.add(ContentItem(product))
            }
        }

        adapter.data = dataSet
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerViewItems.adapter = null
        _binding = null
    }

}