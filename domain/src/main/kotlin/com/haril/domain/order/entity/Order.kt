package com.haril.domain.order.entity

import java.time.LocalDateTime

class Order(
    val id: Long,
    val customerId: Long,
    val orderDate: LocalDateTime,
) {
}
