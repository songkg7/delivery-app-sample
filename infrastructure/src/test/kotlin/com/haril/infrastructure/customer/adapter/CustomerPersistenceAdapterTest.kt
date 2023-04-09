package com.haril.infrastructure.customer.adapter

import com.haril.domain.customer.entity.Customer
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@Import(CustomerPersistenceAdapter::class)
class CustomerPersistenceAdapterTest @Autowired constructor(
    private val customerPersistenceAdapter: CustomerPersistenceAdapter,
) : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        Given("CustomerPersistenceAdapter") {
            When("save customer with id") {
                val customer = Customer(
                    name = "John",
                    address = "서울",
                    phoneNumber = "010-1234-5678",
                )
                val savedCustomer = customerPersistenceAdapter.save(customer)

                Then("should be able to save customer") {
                    savedCustomer shouldNotBe null
                }
            }
        }
    }
}
