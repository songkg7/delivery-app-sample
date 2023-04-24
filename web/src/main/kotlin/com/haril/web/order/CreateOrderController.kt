package com.haril.web.order

import com.haril.application.order.command.CreateOrderCommand
import com.haril.application.order.usecase.CreateOrderUsecase
import com.haril.web.order.request.CreateOrderRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateOrderController(
    private val createOrderUsecase: CreateOrderUsecase,
) {

    @PostMapping("/order")
    fun createOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<String> {
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
