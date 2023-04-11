package com.haril.application.order.service

import com.haril.application.order.command.OrderMenuCommand
import com.haril.application.order.usecase.OrderUsecase
import com.haril.domain.menu.repository.MenuRepository
import com.haril.domain.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val menuRepository: MenuRepository,
) : OrderUsecase {
    override fun order(command: OrderMenuCommand) {
        TODO("Not yet implemented")
    }
}
