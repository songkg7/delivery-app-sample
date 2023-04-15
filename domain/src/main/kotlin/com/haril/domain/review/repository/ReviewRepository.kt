package com.haril.domain.review.repository

import com.haril.domain.review.entity.Review

interface ReviewRepository {
    fun save(review: Review): Review
    fun findById(id: Long): Review
    fun findByCustomerId(customerId: Long): List<Review>
    fun findAll(): List<Review>
    fun deleteById(id: Long)
}
