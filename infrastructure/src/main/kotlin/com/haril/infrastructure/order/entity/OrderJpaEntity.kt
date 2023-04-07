package com.haril.infrastructure.order.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val customerId: Long,
    val orderDate: LocalDateTime,
) {
}
