package com.haril.infrastructure.menu.entity

import com.haril.domain.menu.entity.Menu
import com.haril.infrastructure.restaurant.entity.RestaurantJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "menu")
class MenuJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    val restaurant: RestaurantJpaEntity,
) {
    fun toEntity(): Menu {
        return Menu(
            id = id,
            name = name,
            price = price,
            restaurant = restaurant.toEntity(),
        )
    }

    companion object {
        fun from(menu: Menu): MenuJpaEntity {
            return MenuJpaEntity(
                id = menu.id,
                name = menu.name,
                price = menu.price,
                restaurant = RestaurantJpaEntity.from(menu.restaurant),
            )
        }
    }

}
