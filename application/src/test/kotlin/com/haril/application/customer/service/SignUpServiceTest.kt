package com.haril.application.customer.service

import com.haril.application.customer.command.AddressCommand
import com.haril.application.customer.command.SighUpCommand
import com.haril.domain.customer.entity.Customer
import com.haril.domain.customer.repository.CustomerRepository
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.mockk
import io.mockk.verify

class SignUpServiceTest : DescribeSpec({

    val repository = mockk<CustomerRepository>(relaxed = true)
    val service = SignUpService(repository)

    describe("SignUpService") {
        it("should be able to sign up") {
            val address = AddressCommand(city = "서울", street = "강남구", zipcode = "12345")
            service.signUp(SighUpCommand("John", "010-1234-5678", listOf(address)))

            verify(exactly = 1) { repository.save(any<Customer>()) }
        }
    }

})
