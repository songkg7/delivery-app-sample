package com.haril.infrastructure.restaurant.adapter

import com.haril.domain.restaurant.entity.Restaurant
import com.haril.domain.restaurant.repository.RestaurantRepository
import com.haril.infrastructure.restaurant.entity.RestaurantJpaEntity
import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import org.springframework.stereotype.Component

@Component
class RestaurantPersistenceAdapter(
    private val repository: RestaurantJpaRepository
) : RestaurantRepository {
    override fun save(restaurant: Restaurant): Restaurant {
        return repository.save(RestaurantJpaEntity.from(restaurant)).toEntity()
    }

    // FIXME: CQRS
    // 결국 port 를 분리해야 할까..
    override fun findById(id: Long): Restaurant? {
        return repository.findById(id).map { it.toEntity() }.orElse(null)
    }
}
