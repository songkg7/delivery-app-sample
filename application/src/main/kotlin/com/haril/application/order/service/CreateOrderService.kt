package com.haril.application.order.service

import com.haril.application.order.command.CreateOrderCommand
import com.haril.application.order.usecase.CreateOrderUsecase
import com.haril.domain.order.entity.Order
import com.haril.domain.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class CreateOrderService(
    private val orderRepository: OrderRepository,
//    private val orderMenuRepository: OrderMenuRepository,
) : CreateOrderUsecase {
    override fun create(command: CreateOrderCommand) {
        val order = Order(
            customerId = command.customer.id!!,
        )
        // TODO: 주문과 함께 어떤 메뉴가 주문되었는지 알아야 하므로 주문 메뉴를 생성하는 로직이 필요하다.
        orderRepository.save(order)
    }
}
