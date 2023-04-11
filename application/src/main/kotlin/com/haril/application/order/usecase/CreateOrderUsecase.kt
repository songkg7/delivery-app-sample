package com.haril.application.order.usecase

import com.haril.application.order.command.CreateOrderCommand

interface CreateOrderUsecase {
    fun create(command: CreateOrderCommand)
}
