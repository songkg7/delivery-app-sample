package com.haril.domain.menu.entity

import com.haril.domain.restaurant.entity.Restaurant

class Menu(
    val id: Long? = null,
    val name: String,
    val price: Int,
    val restaurant: Restaurant,
) {
}
