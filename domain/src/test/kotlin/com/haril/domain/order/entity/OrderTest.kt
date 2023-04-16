package com.haril.domain.order.entity

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class OrderTest : BehaviorSpec({

    Given("주문이 생성될 때") {
        Then("deliveryStatus 는 WAITING 이어야 한다.") {
            val order = Order(
                id = 1,
                customerId = 1,
                restaurantId = 1,
            )
            order.deliveryStatus shouldBe Order.DeliveryStatus.WAITING
        }
    }

})
