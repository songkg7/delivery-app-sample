package com.haril.domain.menu.repository

import com.haril.domain.menu.entity.Menu

interface MenuRepository {
    fun save(menu: Menu): Menu

    fun findById(id: Long): Menu?
}
