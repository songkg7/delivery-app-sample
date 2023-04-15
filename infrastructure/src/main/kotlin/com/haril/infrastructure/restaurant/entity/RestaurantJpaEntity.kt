package com.haril.infrastructure.restaurant.entity

import com.haril.domain.restaurant.entity.Restaurant
import jakarta.persistence.*

@Entity
@Table(name = "restaurant")
class RestaurantJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val name: String,
) {

    companion object {
        fun from(restaurant: Restaurant): RestaurantJpaEntity {
            return RestaurantJpaEntity(
                id = restaurant.id,
                name = restaurant.name,
            )
        }
    }

    fun toEntity(): Restaurant {
        return Restaurant(
            id = id,
            name = name,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RestaurantJpaEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
