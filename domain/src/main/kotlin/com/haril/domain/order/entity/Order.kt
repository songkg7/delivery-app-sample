package com.haril.domain.order.entity

import java.time.LocalDateTime

class Order(
    val customerId: Long,
    val orderDate: LocalDateTime = LocalDateTime.now(),
    val deliveryStatus: DeliveryStatus = DeliveryStatus.WAITING,
) {
    enum class DeliveryStatus {
        WAITING, DELIVERING, DELIVERED, CANCEL
    }
}
