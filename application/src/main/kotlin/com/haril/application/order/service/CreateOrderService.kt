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
        ).let { orderRepository.save(it) }
        // order and menu 를 생성하고 orderMenuRepository.save(orderMenu) 를 하면 자동으로 order 와 menu 가 생성될 것이라 예상
        // 주문받은 메뉴의 개수만큼 orderMenu 를 생성 후 saveAll() 을 하면 될 것 같다.
        command.menus.forEach { (menu, count) ->
            repeat(count) {
                orderMenuRepository.save(order, menu)
            }
        }
    }
}
