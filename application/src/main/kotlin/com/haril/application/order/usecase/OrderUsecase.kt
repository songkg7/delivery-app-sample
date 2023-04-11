package com.haril.application.order.usecase

import com.haril.application.order.command.OrderMenuCommand

interface OrderUsecase {
    fun order(command: OrderMenuCommand)
}
