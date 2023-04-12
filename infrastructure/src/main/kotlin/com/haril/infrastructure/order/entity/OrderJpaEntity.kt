package com.haril.infrastructure.order.entity

import com.haril.domain.order.entity.Order
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val customerId: Long,
    val orderDate: LocalDateTime,
    @Enumerated(EnumType.STRING)
    val deliveryStatus: Order.DeliveryStatus = Order.DeliveryStatus.WAITING,
) {
}
