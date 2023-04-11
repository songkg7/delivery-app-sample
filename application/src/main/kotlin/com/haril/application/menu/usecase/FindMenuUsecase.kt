package com.haril.application.menu.usecase

import com.haril.domain.menu.entity.Menu

interface FindMenuUsecase {
    fun find(menuId: Long): Menu
}
