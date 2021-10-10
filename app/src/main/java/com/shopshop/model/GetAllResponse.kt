package com.shopshop.model

data class GetAllResponse(
    val list: List<ItemResponse>
)

data class ItemResponse(
    val title: String,
    val desc: String,
    val data: Long,
    val type: Int
)