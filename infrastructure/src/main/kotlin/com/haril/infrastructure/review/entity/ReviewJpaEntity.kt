package com.haril.infrastructure.review.entity

import com.haril.domain.review.entity.Review
import com.haril.infrastructure.restaurant.entity.RestaurantJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "review")
class ReviewJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @ManyToOne
    val restaurant: RestaurantJpaEntity,

    // NOTE: 간접참조
    val customerId: Long,
    val orderId: Long,
    val content: String,
    val rating: Int,
) {
    companion object {
        fun from(review: Review): ReviewJpaEntity {
            return ReviewJpaEntity(
                id = review.id,
                customerId = review.customerId,
                orderId = review.orderId,
                content = review.content,
                rating = review.rating,
                restaurant = RestaurantJpaEntity.from(review.restaurant)
            )
        }
    }

    fun toEntity(): Review {
        return Review(
            id = id,
            customerId = customerId,
            orderId = orderId,
            content = content,
            rating = rating,
            restaurant = restaurant.toEntity()
        )
    }
}
