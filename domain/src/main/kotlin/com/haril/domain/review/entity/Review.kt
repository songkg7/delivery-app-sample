package com.haril.domain.review.entity

import com.haril.domain.restaurant.entity.Restaurant

class Review(
    val id: Long = 0L,
    val restaurant: Restaurant,
    val customerId: Long,
    val orderId: Long,
    val content: String,
    val rating: Int,
) {
}