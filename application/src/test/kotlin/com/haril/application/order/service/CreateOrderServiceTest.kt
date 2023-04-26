package com.haril.application.order.service

import com.haril.application.order.command.CreateOrderCommand
import com.haril.domain.menu.entity.Menu
import com.haril.domain.order.entity.Order
import com.haril.domain.order.entity.Order.DeliveryStatus
import com.haril.domain.order.repository.OrderRepository
import com.haril.domain.ordermenu.repository.OrderMenuRepository
import com.haril.domain.restaurant.entity.Restaurant
import com.haril.domain.restaurant.repository.RestaurantRepository
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class CreateOrderServiceTest : BehaviorSpec({

    val orderRepository = mockk<OrderRepository>(relaxed = true)
    val orderMenuRepository = mockk<OrderMenuRepository>(relaxed = true)
    val restaurantRepository = mockk<RestaurantRepository>(relaxed = true)
    val service = CreateOrderService(orderRepository, orderMenuRepository, restaurantRepository)

    Given("주문 요청 명세가 주어졌을 때") {
        val restaurant = Restaurant(id = 1, name = "마녀주방")
        restaurant.addMenus(
            Menu(id = 1, name = "pasta", price = 3000, restaurant = restaurant),
            Menu(id = 2, name = "bread", price = 2000, restaurant = restaurant),
        )
        every { restaurantRepository.findById(any()) } returns restaurant
        val command = CreateOrderCommand(
            customerId = 1,
            menus = listOf(
                1L to 1,
                2L to 2,
            ),
            restaurantId = 1,
        )

        xWhen("createOrderService 를 실행하면") {
            service.create(command)

            Then("1개의 order 가 생성되고 주문받은 메뉴 수 만큼의 orderMenu 가 생성된다.") {
                verify(exactly = 1) { orderRepository.save(any<Order>()) }
                verify(exactly = 3) { orderMenuRepository.save(any<Order>(), any<Menu>()) }
            }
        }

        When("고객의 주문이 이미 존재할 때") {
            And("상태가 완료 혹은 취소라면") {
                val status = listOf(DeliveryStatus.DELIVERED, DeliveryStatus.CANCEL)
                Then("주문이 가능하다.") {
                    status.forEach { status ->
                        every { orderRepository.findLatestByCustomerId(any()) } returns Order(
                            customerId = 1,
                            restaurantId = 1,
                            deliveryStatus = status,
                        )
                        service.create(command)
                    }
                    verify { orderRepository.save(any<Order>()) }
                }
            }

            And("상태가 완료 혹은 취소가 아니라면") {
                val status = DeliveryStatus.values()
                    .filterNot { it == DeliveryStatus.DELIVERED || it == DeliveryStatus.CANCEL }
                Then("주문이 불가능하다.") {
                    status.forEach { status ->
                        every { orderRepository.findLatestByCustomerId(any()) } returns Order(
                            customerId = 1,
                            restaurantId = 1,
                            deliveryStatus = status,
                        )
                        val exception = shouldThrowExactly<IllegalStateException> {
                            service.create(command)
                        }
                        exception.message shouldBe "이전 주문이 완료되지 않았습니다."
                    }
                }
            }
        }

    }

})
