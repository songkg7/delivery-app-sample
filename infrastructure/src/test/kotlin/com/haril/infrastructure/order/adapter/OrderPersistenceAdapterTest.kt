package com.haril.infrastructure.order.adapter

import com.haril.domain.order.entity.Order
import com.haril.infrastructure.customer.repository.CustomerJpaRepository
import io.kotest.core.spec.style.FreeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
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
    override fun extensions() = listOf(SpringExtension)

    init {
        // FIXME: mocking 이 정상적으로 동작하지 않음
        "!새로운 주문을 저장할 때" - {
            val customerJpaRepository = mockk<CustomerJpaRepository>()
            every { customerJpaRepository.existsById(any()) } returns true

            "order id 가 null 이라면" - {
                val savedOrder = Order(
                    id = null,
                    customerId = 1L,
                    restaurantId = 1L,
                ).let { orderPersistenceAdapter.save(it) }

                "새로운 id 가 생성되며 저장된다." {
                    savedOrder.id shouldNotBe null
                }
            }

        }
    }
}
