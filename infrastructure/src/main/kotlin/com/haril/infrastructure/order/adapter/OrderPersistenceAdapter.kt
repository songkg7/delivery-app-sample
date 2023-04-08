package com.haril.infrastructure.order.adapter

import com.haril.domain.order.entity.Order
import com.haril.domain.order.repository.OrderRepository
import com.haril.infrastructure.order.entity.OrderJpaEntity
import com.haril.infrastructure.order.repository.OrderJpaRepository
import org.springframework.stereotype.Component

@Component
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository
) : OrderRepository {

    override fun save(order: Order): Order {
        return orderJpaRepository.save(
            OrderJpaEntity(
                id = order.id,
                customerId = order.customerId,
                orderDate = order.orderDate,
            )
        ).let {
            Order(
                id = it.id,
                customerId = it.customerId,
                orderDate = it.orderDate,
            )
        }
    }
}
