package com.haril.web.order

import com.haril.application.customer.command.FindCustomerCommand
import com.haril.application.customer.usecase.FindCustomerUsecase
import com.haril.application.menu.usecase.FindMenuUsecase
import com.haril.application.order.command.CreateOrderCommand
import com.haril.application.order.usecase.CreateOrderUsecase
import com.haril.web.order.request.CreateOrderRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateOrderController(
    private val createOrderUsecase: CreateOrderUsecase,
    private val findCustomerUsecase: FindCustomerUsecase,
    private val findMenuUsecase: FindMenuUsecase,
) {

    @PostMapping("/order")
    fun createOrder(@RequestBody request: CreateOrderRequest): String {
        val customer = findCustomerUsecase.findCustomer(FindCustomerCommand(request.customerId))
        val menus = request.menus.map { findMenuUsecase.find(it.menuId) to it.quantity }
        createOrderUsecase.create(CreateOrderCommand(customer, menus))
        // TODO: response model
        return "ok"
    }

}
