package com.haril.web.review.request

data class CreateReviewRequest(
    val customerId: Long,
    val restaurantId: Long,
    val orderId: Long,
    val content: String,
    val rating: Int,
)
