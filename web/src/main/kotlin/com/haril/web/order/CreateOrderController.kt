package com.haril.web.order

import com.haril.application.customer.usecase.FindCustomerUsecase
import com.haril.application.menu.usecase.FindMenuUsecase
import com.haril.application.order.command.CreateOrderCommand
import com.haril.application.order.usecase.CreateOrderUsecase
import com.haril.application.restaurant.usecase.FindRestaurantUsecase
import com.haril.web.order.request.CreateOrderRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateOrderController(
    private val createOrderUsecase: CreateOrderUsecase,
    private val findCustomerUsecase: FindCustomerUsecase,
    private val findMenuUsecase: FindMenuUsecase,
    private val findRestaurantUsecase: FindRestaurantUsecase,
) {

    @PostMapping("/order")
    fun createOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<String> {
//        val customer = findCustomerUsecase.findCustomer(FindCustomerCommand(request.customerId))
//        val restaurant = findRestaurantUsecase.find(FindRestaurantCommand(request.restaurantId))
//        val menus = request.menus.map { findMenuUsecase.find(it.menuId) to it.quantity }
        createOrderUsecase.create(
            CreateOrderCommand(
                customerId = request.customerId,
                restaurantId = request.restaurantId,
                menus = request.menus.map { it.menuId to it.quantity })
        )
        // TODO: response model
        return ResponseEntity.ok().build()
    }

}
