package com.haril.infrastructure.menu.repository

import com.haril.infrastructure.menu.entity.MenuJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuJpaRepository : JpaRepository<MenuJpaEntity, Long> {
}
