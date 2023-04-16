package com.haril.application.review.command

import com.haril.domain.customer.entity.Customer
import com.haril.domain.order.entity.Order
import com.haril.domain.restaurant.entity.Restaurant

class CreateReviewCommand(
    val customerId: Long,
    val restaurantId: Long,
    val orderId: Long,
    val content: String,
    val rating: Int,
) {
}
