package com.haril.domain.restaurant.entity

import com.haril.domain.menu.entity.Menu

class Restaurant(val id: Long? = null, val name: String, menus: List<Menu> = emptyList()) {
    private val _menus: MutableList<Menu> = mutableListOf()
    val menus: List<Menu>
        get() = _menus.toList()

    init {
        this._menus.addAll(menus)
    }

    fun addMenus(vararg menus: Menu) {
        this._menus.addAll(menus)
    }

    fun addMenus(menus: List<Menu>) {
        this._menus.addAll(menus)
    }
}
