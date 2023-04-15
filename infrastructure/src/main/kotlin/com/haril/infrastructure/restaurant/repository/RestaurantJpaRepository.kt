package com.haril.infrastructure.restaurant.repository

import com.haril.infrastructure.restaurant.entity.RestaurantJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantJpaRepository : JpaRepository<RestaurantJpaEntity, Long> {
}
