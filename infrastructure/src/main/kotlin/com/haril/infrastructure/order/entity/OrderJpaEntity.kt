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
    // TODO: ManyToOne
    val customerId: Long,
    val restaurantId: Long,
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
                restaurantId = order.restaurantId,
            )
        }
    }

    fun toEntity(): Order {
        return Order(
            id = id,
            customerId = customerId,
            orderDate = orderDate,
            deliveryStatus = deliveryStatus,
            restaurantId = restaurantId,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderJpaEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
