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

    companion object {
        fun from(order: Order): OrderJpaEntity {
            return OrderJpaEntity(
                id = order.id,
                customerId = order.customerId,
                orderDate = order.orderDate,
                deliveryStatus = order.deliveryStatus,
            )
        }
    }

    fun toEntity(): Order {
        return Order(
            id = id,
            customerId = customerId,
            orderDate = orderDate,
            deliveryStatus = deliveryStatus,
        )
    }

}
