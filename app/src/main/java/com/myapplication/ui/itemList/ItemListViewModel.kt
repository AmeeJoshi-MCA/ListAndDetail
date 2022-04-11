package com.myapplication.ui.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.AppRepository
import com.myapplication.util.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _dataResult = MutableLiveData<DataResult>()
    val dataResult: LiveData<DataResult> = _dataResult

    fun getItems() = viewModelScope.launch {
        _dataResult.value = DataResult.Empty
        appRepository.getItems().collect { _dataResult.value = it }
    }
}