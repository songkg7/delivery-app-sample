package com.haril.domain.order.entity

import java.time.LocalDateTime

class Order(
    val id: Long? = null,
    val customerId: Long,
    val restaurantId: Long,
    val orderDate: LocalDateTime = LocalDateTime.now(),
    val deliveryStatus: DeliveryStatus = DeliveryStatus.WAITING,
) {
    enum class DeliveryStatus {
        WAITING, DELIVERING, DELIVERED, CANCEL
    }
}
