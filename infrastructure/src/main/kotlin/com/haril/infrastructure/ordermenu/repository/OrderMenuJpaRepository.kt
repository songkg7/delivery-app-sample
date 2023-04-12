package com.haril.infrastructure.ordermenu.repository

import com.haril.infrastructure.ordermenu.entity.OrderMenuJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderMenuJpaRepository: JpaRepository<OrderMenuJpaEntity, Long>
