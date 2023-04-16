package com.haril.domain.restaurant.repository

import com.haril.domain.restaurant.entity.Restaurant

interface RestaurantRepository {
    fun save(restaurant: Restaurant): Restaurant

    fun findById(id: Long): Restaurant
}
