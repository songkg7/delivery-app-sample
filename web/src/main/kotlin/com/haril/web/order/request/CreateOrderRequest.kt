package com.haril.web.order.request

data class CreateOrderRequest(
    val customerId: Long,
    val menus: List<OrderSheet>,
)

data class OrderSheet(
    val menuId: Long,
    val quantity: Int,
)
