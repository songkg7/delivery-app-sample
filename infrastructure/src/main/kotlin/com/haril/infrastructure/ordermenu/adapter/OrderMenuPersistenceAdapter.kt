package com.haril.infrastructure.ordermenu.adapter

import com.haril.domain.menu.entity.Menu
import com.haril.domain.order.entity.Order
import com.haril.domain.ordermenu.entity.OrderMenu
import com.haril.domain.ordermenu.repository.OrderMenuRepository
import com.haril.infrastructure.menu.entity.MenuJpaEntity
import com.haril.infrastructure.order.entity.OrderJpaEntity
import com.haril.infrastructure.ordermenu.entity.OrderMenuJpaEntity
import com.haril.infrastructure.ordermenu.repository.OrderMenuJpaRepository
import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import org.springframework.stereotype.Component

@Component
class OrderMenuPersistenceAdapter(
    private val orderMenuJpaRepository: OrderMenuJpaRepository,
    private val restaurantJpaRepository: RestaurantJpaRepository,
): OrderMenuRepository {
    override fun save(order: Order, menu: Menu): OrderMenu {
        val restaurantJpaEntity = restaurantJpaRepository.findById(order.restaurantId).orElseThrow()
        return OrderMenuJpaEntity(
            order = OrderJpaEntity.from(order),
            menu = MenuJpaEntity.from(menu, restaurantJpaEntity),
        ).let {
            orderMenuJpaRepository.save(it)
        }.toEntity()
    }

}
