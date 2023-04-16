package com.haril.application.order.usecase

import com.haril.application.order.command.FindOrderCommand
import com.haril.domain.order.entity.Order

interface FindOrderUsecase {
    fun findOrder(command: FindOrderCommand): Order
}
