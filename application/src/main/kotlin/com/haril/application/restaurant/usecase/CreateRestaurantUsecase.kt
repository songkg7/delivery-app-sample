package com.haril.application.restaurant.usecase

import com.haril.application.restaurant.command.CreateRestaurantCommand

interface CreateRestaurantUsecase {
    fun create(command: CreateRestaurantCommand)
}
