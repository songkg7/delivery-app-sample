package com.haril.infrastructure.menu.entity

import com.haril.domain.menu.entity.Menu
import com.haril.domain.restaurant.entity.Restaurant
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
    val restaurant: RestaurantJpaEntity? = null,
) {

    fun toEntity(restaurant: Restaurant): Menu {
        return Menu(
            id = this.id,
            name = name,
            price = price,
            restaurant = restaurant,
        )
    }

    companion object {
        fun from(menu: Menu): MenuJpaEntity {
            return MenuJpaEntity(
                id = menu.id,
                name = menu.name,
                price = menu.price,
                // FIXME: set restaurant
            )
        }
    }

}
