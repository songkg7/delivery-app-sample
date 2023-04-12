package com.haril.domain.ordermenu.repository

import com.haril.domain.menu.entity.Menu
import com.haril.domain.order.entity.Order
import com.haril.domain.ordermenu.entity.OrderMenu

// ingoing port
interface OrderMenuRepository {
    fun save(order: Order, menu: Menu): OrderMenu
}
