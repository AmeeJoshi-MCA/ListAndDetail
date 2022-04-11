package com.myapplication.util


import com.myapplication.network.model.ApiItemList
import com.myapplication.network.model.Product

/**
 * This class will be used to create header and section in recyclerView list.
 */

open class RecyclerViewItem
class SectionItem(val apiItemList:  ApiItemList) : RecyclerViewItem()
class ContentItem(val product: Product) : RecyclerViewItem()

