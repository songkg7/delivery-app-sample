package com.haril.application.restaurant.service

import com.haril.application.restaurant.command.CreateRestaurantCommand
import com.haril.application.restaurant.usecase.CreateRestaurantUsecase
import com.haril.domain.menu.entity.Menu
import com.haril.domain.restaurant.entity.Restaurant
import com.haril.domain.restaurant.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class CreateRestaurantService(
    private val restaurantRepository: RestaurantRepository,
) : CreateRestaurantUsecase {

    override fun create(command: CreateRestaurantCommand) {
        val restaurant = Restaurant(
            name = command.name,
        )
        restaurant.addMenus(
            command.menus.map {
                Menu(
                    name = it.name,
                    price = it.price,
                    restaurant = restaurant,
                )
            }
        )

        restaurantRepository.save(restaurant)
    }
}
