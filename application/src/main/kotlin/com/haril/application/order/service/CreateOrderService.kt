package com.haril.application.order.service

import com.haril.application.order.command.CreateOrderCommand
import com.haril.application.order.usecase.CreateOrderUsecase
import com.haril.domain.order.entity.Order
import com.haril.domain.order.repository.OrderRepository
import com.haril.domain.ordermenu.repository.OrderMenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateOrderService(
    private val orderRepository: OrderRepository,
    private val orderMenuRepository: OrderMenuRepository,
) : CreateOrderUsecase {
    override fun create(command: CreateOrderCommand) {
        val order = Order(
            customerId = command.customer.id!!,
            restaurantId = command.restaurant.id!!,
        ).let { orderRepository.save(it) }

        command.menus.forEach { (menu, count) ->
            repeat(count) { orderMenuRepository.save(order, menu) }
        }
    }
}
