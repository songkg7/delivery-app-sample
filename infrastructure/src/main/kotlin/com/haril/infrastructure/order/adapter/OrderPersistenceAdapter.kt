package com.haril.infrastructure.order.adapter

import com.haril.domain.order.entity.Order
import com.haril.domain.order.repository.OrderRepository
import com.haril.infrastructure.customer.repository.CustomerJpaRepository
import com.haril.infrastructure.order.entity.OrderJpaEntity
import com.haril.infrastructure.order.repository.OrderJpaRepository
import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import org.springframework.stereotype.Component

@Component
class OrderPersistenceAdapter(
    // TODO: implement outgoing port and incoming port
    private val orderJpaRepository: OrderJpaRepository,
    private val customerJpaRepository: CustomerJpaRepository,
    private val restaurantJpaRepository: RestaurantJpaRepository,
) : OrderRepository {

    override fun save(order: Order): Order {
        check(customerJpaRepository.existsById(order.customerId)) { "customer not found" }
        check(restaurantJpaRepository.existsById(order.restaurantId)) { "restaurant not found" }

        return orderJpaRepository.save(
            OrderJpaEntity(
                id = order.id,
                customerId = order.customerId,
                orderDate = order.orderDate,
                restaurantId = order.restaurantId,
            )
        ).toEntity()
    }

    override fun findById(id: Long): Order {
        return orderJpaRepository.findById(id).orElseThrow().toEntity()
    }
}
