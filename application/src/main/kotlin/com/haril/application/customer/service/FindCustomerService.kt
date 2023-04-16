package com.haril.application.customer.service

import com.haril.application.customer.command.FindCustomerCommand
import com.haril.application.customer.usecase.FindCustomerUsecase
import com.haril.domain.customer.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FindCustomerService(
    private val customerRepository: CustomerRepository,
) : FindCustomerUsecase {
    override fun findCustomer(command: FindCustomerCommand) = customerRepository.findById(command.customerId)
}
