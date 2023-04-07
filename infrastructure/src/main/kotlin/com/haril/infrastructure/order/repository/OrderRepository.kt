package com.haril.infrastructure.order.repository

import com.haril.domain.order.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}
