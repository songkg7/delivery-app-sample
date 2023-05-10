package com.haril.infrastructure.customer.adapter

import com.haril.domain.address.entity.CustomerAddress
import com.haril.domain.customer.entity.Customer
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldContainExactly
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
        Given("고객이 회원가입을 할 때") {
            When("save customer with id") {
                val customer = Customer(
                    name = "John",
                    phoneNumber = "010-1234-5678",
                )
                val savedCustomer = customerPersistenceAdapter.save(customer)

                Then("should be able to save customer") {
                    savedCustomer shouldNotBe null
                }
            }

            val address = CustomerAddress(
                city = "서울시 강남구",
                street = "강남대로 1234",
                zipcode = "123-456",
            )
            val customer = Customer(
                name = "John",
                phoneNumber = "010-1234-5678",
                addresses = listOf(address),
            )
            val savedCustomer = customerPersistenceAdapter.save(customer)
            When("주소를 입력할 경우") {
                Then("고객과 주소가 함께 저장된다.") {
                    savedCustomer shouldNotBe null
                    savedCustomer.addresses shouldContainExactly listOf(address)
                }
            }

            xWhen("고객이 가진 주소 목록에서 일부를 추가할 경우") {
                val address2 = CustomerAddress(
                    city = "서울시 동작구",
                    street = "동작대로 1234",
                    zipcode = "567-123",
                )
                savedCustomer.addAddresses(address2)
                Then("고객이 가진 주소 목록에 해당 주소가 추가된다.") {
                    savedCustomer.addresses shouldContainExactly listOf(address, address2)
                }
            }

            xWhen("고객이 가진 주소 목록에서 일부를 제거할 경우") {
                Then("고객이 가진 주소 중 해당 주소만 제거된다.") {

                }
            }

        }
    }
}
