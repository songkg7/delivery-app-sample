package com.haril.application.menu.service

import com.haril.application.menu.usecase.FindMenuUsecase
import com.haril.domain.menu.entity.Menu
import com.haril.domain.menu.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class FindMenuService(
    private val menuRepository: MenuRepository,
) : FindMenuUsecase {
    override fun find(menuId: Long): Menu {
        return menuRepository.findById(menuId) ?: throw Exception("존재하지 않는 menu 입니다.")
    }
}
