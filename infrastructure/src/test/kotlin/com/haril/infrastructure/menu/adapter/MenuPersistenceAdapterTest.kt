package com.haril.infrastructure.menu.adapter

import com.haril.domain.menu.entity.Menu
import com.haril.domain.restaurant.entity.Restaurant
import com.haril.infrastructure.menu.repository.MenuJpaRepository
import com.haril.infrastructure.restaurant.entity.RestaurantJpaEntity
import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldHaveSize
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@Import(MenuPersistenceAdapter::class)
class MenuPersistenceAdapterTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var menuJpaRepository: MenuJpaRepository

    @Autowired
    lateinit var persistenceAdapter: MenuPersistenceAdapter

    @Autowired
    lateinit var restaurantRepository: RestaurantJpaRepository

    init {
        Given("레스토랑이 존재하는 경우") {
            val restaurant = Restaurant(name = "마녀주방")
            val savedRestaurant = restaurantRepository.save(RestaurantJpaEntity.from(restaurant)).toEntity()

            When("새로운 메뉴에 레스토랑 정보를 전달해주면") {
                val menu = Menu(name = "test", price = 1000, restaurant = savedRestaurant)
                persistenceAdapter.save(menu)

                Then("메뉴가 등록된다.") {
                    menuJpaRepository.findAll() shouldHaveSize 1
                }
            }
        }
    }

}
