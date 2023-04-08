package com.haril.infrastructure.customer.repository

import com.haril.infrastructure.customer.entity.CustomerJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerJpaRepository : JpaRepository<CustomerJpaEntity, Long> {
}
