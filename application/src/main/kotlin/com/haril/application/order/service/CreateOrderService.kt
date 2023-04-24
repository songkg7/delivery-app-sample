package com.haril.application.order.service

import com.haril.application.order.command.CreateOrderCommand
import com.haril.application.order.usecase.CreateOrderUsecase
import com.haril.domain.order.entity.Order
import com.haril.domain.order.repository.OrderRepository
import com.haril.domain.ordermenu.repository.OrderMenuRepository
import com.haril.domain.restaurant.repository.RestaurantRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateOrderService(
    private val orderRepository: OrderRepository,
    private val orderMenuRepository: OrderMenuRepository,
    private val restaurantRepository: RestaurantRepository,
) : CreateOrderUsecase {
    override fun create(command: CreateOrderCommand) {
        val order = Order(
            customerId = command.customerId,
            restaurantId = command.restaurantId,
        ).let { orderRepository.save(it) }

        val restaurant = restaurantRepository.findById(command.restaurantId)
        command.menus.forEach { (menuId, count) ->
            val menu = restaurant.menus.find { it.id == menuId }
            require(menu != null) { "메뉴가 존재하지 않습니다." }
            repeat(count) { orderMenuRepository.save(order, menu) }
        }
    }
}
