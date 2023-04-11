package com.haril.application.order.command

// 주문관련 생성은 Order 에서만 이루어진다.
// 메뉴 없이 주문할 수 없다.
class OrderMenuCommand(
    val customerId: Long,
    val menuMap: Map<Long, Int>
) {
}
