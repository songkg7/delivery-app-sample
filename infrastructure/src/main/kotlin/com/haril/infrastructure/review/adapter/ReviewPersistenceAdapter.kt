package com.haril.infrastructure.review.adapter

import com.haril.domain.review.entity.Review
import com.haril.domain.review.repository.ReviewRepository
import com.haril.infrastructure.review.entity.ReviewJpaEntity
import com.haril.infrastructure.review.repsitory.ReviewJpaRepository
import org.springframework.stereotype.Component

@Component
class ReviewPersistenceAdapter(
    private val repository: ReviewJpaRepository
): ReviewRepository {
    override fun save(review: Review): Review {
        return repository.save(ReviewJpaEntity.from(review)).toEntity()
    }

    override fun findById(id: Long): Review {
        return repository.findById(id).orElseThrow().toEntity()
    }

    override fun findByCustomerId(customerId: Long): List<Review> {
        return repository.findByCustomerId(customerId).map { it.toEntity() }
    }

    override fun findAll(): List<Review> = repository.findAll().map { it.toEntity() }

    override fun deleteById(id: Long) = repository.deleteById(id)
}
