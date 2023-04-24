package com.haril.infrastructure.restaurant.entity

import com.haril.domain.restaurant.entity.Restaurant
import com.haril.infrastructure.menu.entity.MenuJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "restaurant")
class RestaurantJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val name: String,
    @OneToMany(mappedBy = "restaurant", cascade = [CascadeType.ALL], orphanRemoval = true)
    val menus: List<MenuJpaEntity> = emptyList(),
) {

    companion object {
        fun from(restaurant: Restaurant): RestaurantJpaEntity {
            return RestaurantJpaEntity(
                id = restaurant.id,
                name = restaurant.name,
                menus = restaurant.menus.map { MenuJpaEntity.from(it) },
            )
        }
    }

    fun toEntity(): Restaurant {
        val restaurant = Restaurant(id = id, name = name)
        restaurant.addMenus(menus.map { it.toEntity(restaurant) })
        return restaurant
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RestaurantJpaEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
