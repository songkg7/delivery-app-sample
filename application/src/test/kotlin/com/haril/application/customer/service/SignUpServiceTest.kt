package com.haril.application.customer.service

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
            service.signUp(SighUpCommand("John", "서울", "010-1234-5678"))

            verify(exactly = 1) { repository.save(any<Customer>()) }
        }
    }

})
