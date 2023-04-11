package com.haril.infrastructure.menu.entity

import com.haril.domain.menu.entity.Menu
import jakarta.persistence.*

@Entity
@Table(name = "menu")
class MenuJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val price: Int,
){
    fun toEntity(): Menu {
        return Menu(
            id = id,
            name = name,
            price = price,
        )
    }

    companion object {
        fun from(menu: Menu): MenuJpaEntity {
            return MenuJpaEntity(
                id = menu.id,
                name = menu.name,
                price = menu.price,
            )
        }
    }

}
