package com.haril.infrastructure.review.repsitory

import com.haril.infrastructure.review.entity.ReviewJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewJpaRepository: JpaRepository<ReviewJpaEntity, Long> {
    fun findByCustomerId(customerId: Long): List<ReviewJpaEntity>
}
