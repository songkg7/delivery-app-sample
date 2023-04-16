package com.haril.application.restaurant.usecase

import com.haril.application.restaurant.command.FindRestaurantCommand
import com.haril.domain.restaurant.entity.Restaurant

interface FindRestaurantUsecase {
    fun find(command: FindRestaurantCommand): Restaurant
}
