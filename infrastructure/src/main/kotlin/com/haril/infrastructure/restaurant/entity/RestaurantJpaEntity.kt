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
    menus: List<MenuJpaEntity> = emptyList(),
) {

    @OneToMany(mappedBy = "restaurant", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val _menus: MutableList<MenuJpaEntity> = mutableListOf()

    val menus: List<MenuJpaEntity>
        get() = _menus.toList()

    init {
        this._menus.addAll(menus)
    }

    companion object {
        fun from(restaurant: Restaurant): RestaurantJpaEntity {
            val restaurantJpaEntity = RestaurantJpaEntity(
                id = restaurant.id,
                name = restaurant.name,
            )
            restaurantJpaEntity.addMenus(restaurant.menus.map { MenuJpaEntity.from(it, restaurantJpaEntity) })
            return restaurantJpaEntity
        }
    }

    private fun addMenus(menus: List<MenuJpaEntity>) {
        this._menus.addAll(menus)
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
