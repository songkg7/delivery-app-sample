package com.haril.infrastructure.order.adapter

import com.haril.domain.order.entity.Order
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@Import(OrderPersistenceAdapter::class)
class OrderPersistenceAdapterTest @Autowired constructor(
    private val orderPersistenceAdapter: OrderPersistenceAdapter,
) : FreeSpec() {

    init {
        "새로운 주문을 저장할 때" - {

            "order id 가 null 이라면" - {
                val savedOrder = Order(
                    id = null,
                    customerId = 1,
                    restaurantId = 1,
                ).let { orderPersistenceAdapter.save(it) }

                "새로운 id 가 생성되며 저장된다." {
                    savedOrder.id shouldNotBe null
                }
            }

        }
    }
}
