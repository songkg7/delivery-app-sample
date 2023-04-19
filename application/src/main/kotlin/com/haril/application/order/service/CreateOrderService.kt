package com.haril.application.order.service

import com.haril.application.order.command.CreateOrderCommand
import com.haril.application.order.usecase.CreateOrderUsecase
import com.haril.domain.menu.repository.MenuRepository
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
    private val menuRepository: MenuRepository,
) : CreateOrderUsecase {
    override fun create(command: CreateOrderCommand) {
        val order = Order(
            customerId = command.customerId,
            restaurantId = command.restaurantId,
        ).let { orderRepository.save(it) }


        command.menus.forEach { (menuId, count) ->
            val menu = menuRepository.findById(menuId)
            repeat(count) { orderMenuRepository.save(order, menu) }
        }
    }
}
