package com.haril.domain.order.entity

import java.time.LocalDate

class Order(
    val id: Long,
    val customerId: Long,
    val orderDate: LocalDate,
) {
}
