package com.haril.infrastructure.order.repository

import com.haril.infrastructure.order.entity.OrderJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderJpaRepository : JpaRepository<OrderJpaEntity, Long> {
}
