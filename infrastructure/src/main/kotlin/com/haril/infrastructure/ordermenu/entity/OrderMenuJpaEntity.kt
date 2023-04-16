package com.haril.infrastructure.ordermenu.entity

import com.haril.domain.ordermenu.entity.OrderMenu
import com.haril.infrastructure.menu.entity.MenuJpaEntity
import com.haril.infrastructure.order.entity.OrderJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "orders_menu")
class OrderMenuJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: OrderJpaEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    val menu: MenuJpaEntity,
) {

    fun toEntity(): OrderMenu {
        return OrderMenu(
            id = id,
            orderId = order.id!!,
            menuId = menu.id!!,
        )
    }

}
