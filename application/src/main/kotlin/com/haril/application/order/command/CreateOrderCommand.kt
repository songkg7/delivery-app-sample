package com.haril.application.order.command

class CreateOrderCommand(
    val customerId: Long,
    val restaurantId: Long,
    val menus: List<Pair<Long, Int>>,
) {
}
