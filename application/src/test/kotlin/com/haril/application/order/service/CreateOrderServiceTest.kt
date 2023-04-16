package com.haril.application.order.service

import com.haril.application.order.command.CreateOrderCommand
import com.haril.domain.customer.entity.Customer
import com.haril.domain.menu.entity.Menu
import com.haril.domain.order.entity.Order
import com.haril.domain.order.repository.OrderRepository
import com.haril.domain.ordermenu.repository.OrderMenuRepository
import com.haril.domain.restaurant.entity.Restaurant
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify

class CreateOrderServiceTest : BehaviorSpec({

    val orderRepository = mockk<OrderRepository>(relaxed = true)
    val orderMenuRepository = mockk<OrderMenuRepository>(relaxed = true)

    Given("주문 요청 명세가 주어졌을 때") {
        val restaurant = Restaurant(1, "마녀주방")
        val command = CreateOrderCommand(
            customer = Customer(1, "홍길동", "서울시 강남구", "010-1234-5678"),
            menus = listOf(
                Menu(1, "피자", 10000, restaurant) to 1,
                Menu(2, "햄버거", 5000, restaurant) to 2,
            ),
            restaurant = restaurant,
        )

        When("createOrderService 를 실행하면") {
            val service = CreateOrderService(orderRepository, orderMenuRepository)
            service.create(command)

            Then("1개의 order 가 생성되고 주문받은 메뉴 수 만큼의 orderMenu 가 생성된다.") {
                verify(exactly = 1) { orderRepository.save(any<Order>()) }
                verify(exactly = 3) { orderMenuRepository.save(any<Order>(), any<Menu>()) }
            }
        }
    }

})
