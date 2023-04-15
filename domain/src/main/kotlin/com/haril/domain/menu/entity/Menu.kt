package com.haril.domain.menu.entity

import com.haril.domain.restaurant.entity.Restaurant

class Menu(
    val id: Long? = null,
    val name: String,
    val price: Int,
    // TODO: Restaurant 객체를 넘기는 것이 아니라 Restaurant의 id를 넘기는 것은 어떨까?
    val restaurant: Restaurant,
) {
}
