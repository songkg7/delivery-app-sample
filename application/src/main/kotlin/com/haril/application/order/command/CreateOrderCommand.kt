package com.haril.application.order.command

import com.haril.domain.customer.entity.Customer
import com.haril.domain.menu.entity.Menu

// 주문관련 생성은 Order 에서만 이루어진다.
// 메뉴 없이 주문할 수 없다.
class CreateOrderCommand(
    val customer: Customer,
    val menus: List<Pair<Menu, Int>>,
) {
}
