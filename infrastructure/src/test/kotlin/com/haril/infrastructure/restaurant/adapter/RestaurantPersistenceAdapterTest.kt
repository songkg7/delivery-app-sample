package com.haril.infrastructure.restaurant.adapter

import com.haril.domain.menu.entity.Menu
import com.haril.domain.restaurant.entity.Restaurant
import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
@Import(RestaurantPersistenceAdapter::class)
class RestaurantPersistenceAdapterTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var restaurantRepository: RestaurantJpaRepository

    @Autowired
    lateinit var persistenceAdapter: RestaurantPersistenceAdapter

    init {
        Given("레스토랑을 저장할 때") {
            val restaurant = Restaurant(name = "마녀주방")
            When("메뉴를 같이 생성하면") {
                val menu1 = Menu(name = "pasta", price = 3000, restaurant = restaurant)
                val menu2 = Menu(name = "bread", price = 2000, restaurant = restaurant)
                restaurant.addMenus(menu1, menu2)
                Then("레스토랑과 메뉴가 함께 저장된다.") {
                    val savedRestaurant = persistenceAdapter.save(restaurant)
                    savedRestaurant.menus.size shouldBe 2
                }
            }
        }
    }

}
