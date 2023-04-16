package com.haril.application.restaurant.service

import com.haril.application.restaurant.command.FindRestaurantCommand
import com.haril.application.restaurant.usecase.FindRestaurantUsecase
import com.haril.domain.restaurant.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class FindRestaurantService(
    private val restaurantRepository: RestaurantRepository,
) : FindRestaurantUsecase {

    override fun find(command: FindRestaurantCommand) =
        restaurantRepository.findById(command.restaurantId)

}
