package com.haril.domain.order.repository

import com.haril.domain.order.entity.Order

interface OrderRepository {
    fun save(order: Order): Order

    fun findById(id: Long): Order
}
