package com.haril.infrastructure.restaurant.adapter

import com.haril.domain.restaurant.entity.Restaurant
import com.haril.domain.restaurant.repository.RestaurantRepository
import com.haril.infrastructure.restaurant.entity.RestaurantJpaEntity
import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import org.springframework.stereotype.Component

@Component
class RestaurantPersistenceAdapter(
    private val repository: RestaurantJpaRepository
): RestaurantRepository {
    override fun save(restaurant: Restaurant): Restaurant {
        return repository.save(RestaurantJpaEntity.from(restaurant)).toEntity()
    }
}
