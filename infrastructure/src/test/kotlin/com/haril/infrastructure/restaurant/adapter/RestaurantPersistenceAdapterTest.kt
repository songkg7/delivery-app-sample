package com.haril.infrastructure.restaurant.adapter

import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
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
    }

}
