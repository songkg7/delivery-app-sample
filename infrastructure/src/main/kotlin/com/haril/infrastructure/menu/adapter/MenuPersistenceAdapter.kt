package com.haril.infrastructure.menu.adapter

import com.haril.domain.menu.entity.Menu
import com.haril.domain.menu.repository.MenuRepository
import com.haril.infrastructure.menu.entity.MenuJpaEntity
import com.haril.infrastructure.menu.repository.MenuJpaRepository
import org.springframework.stereotype.Component

@Component
class MenuPersistenceAdapter(
    private val menuJpaRepository: MenuJpaRepository,
) : MenuRepository {
    override fun save(menu: Menu): Menu {
        return menuJpaRepository.save(MenuJpaEntity.from(menu)).toEntity()
    }

    override fun findById(id: Long): Menu {
        return menuJpaRepository.findById(id).map { it.toEntity() }.orElseThrow()
    }
}
