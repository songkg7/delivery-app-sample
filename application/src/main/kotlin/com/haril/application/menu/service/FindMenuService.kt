package com.haril.application.menu.service

import com.haril.application.menu.usecase.FindMenuUsecase
import com.haril.domain.menu.entity.Menu
import com.haril.domain.menu.repository.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FindMenuService(
    private val menuRepository: MenuRepository,
) : FindMenuUsecase {
    override fun find(menuId: Long): Menu {
        return menuRepository.findById(menuId)
    }
}
