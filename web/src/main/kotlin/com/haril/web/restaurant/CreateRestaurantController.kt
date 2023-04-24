package com.haril.web.restaurant

import com.haril.application.restaurant.command.CreateRestaurantCommand
import com.haril.application.restaurant.usecase.CreateRestaurantUsecase
import com.haril.web.restaurant.request.CreateRestaurantRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateRestaurantController(
    private val createRestaurantUsecase: CreateRestaurantUsecase,
) {

    @PostMapping("/restaurant")
    fun createRestaurant(@RequestBody request: CreateRestaurantRequest) {
        createRestaurantUsecase.create(
            CreateRestaurantCommand(
                name = request.name,
                // FIXME: too many Menu model
                menus = request.menus.map {
                    CreateRestaurantCommand.Menu(
                        name = it.name,
                        price = it.price,
                    )
                },
                address = request.address,
                phoneNumber = request.phoneNumber,
                businessHours = request.businessHours,
            )
        )
    }

}
