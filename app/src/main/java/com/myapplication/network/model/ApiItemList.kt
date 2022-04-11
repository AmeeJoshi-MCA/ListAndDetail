package com.myapplication.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ApiItemList(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("products")
    val products: List<Product>
)

@Parcelize
data class Product(
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("salePrice")
    val salePrice: SalePrice,
    @SerializedName("url")
    val url: String
) : Parcelable

@Parcelize
data class SalePrice(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
) : Parcelable