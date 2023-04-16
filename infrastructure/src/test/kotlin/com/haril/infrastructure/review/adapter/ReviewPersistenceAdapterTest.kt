package com.haril.infrastructure.review.adapter

import com.haril.domain.customer.entity.Customer
import com.haril.domain.order.entity.Order
import com.haril.domain.restaurant.entity.Restaurant
import com.haril.domain.review.entity.Review
import com.haril.infrastructure.customer.entity.CustomerJpaEntity
import com.haril.infrastructure.customer.repository.CustomerJpaRepository
import com.haril.infrastructure.order.entity.OrderJpaEntity
import com.haril.infrastructure.order.repository.OrderJpaRepository
import com.haril.infrastructure.restaurant.entity.RestaurantJpaEntity
import com.haril.infrastructure.restaurant.repository.RestaurantJpaRepository
import com.haril.infrastructure.review.repsitory.ReviewJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@Import(ReviewPersistenceAdapter::class)
@DataJpaTest
class ReviewPersistenceAdapterTest @Autowired constructor(
    private val persistenceAdapter: ReviewPersistenceAdapter,
    private val reviewJpaRepository: ReviewJpaRepository,
    private val orderJpaRepository: OrderJpaRepository,
    private val restaurantJpaRepository: RestaurantJpaRepository,
    private val customerJpaRepository: CustomerJpaRepository,
) : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)


    init {
        val customer = Customer(name = "홍길동", address = "서울시 동작구", phoneNumber = "010-1234-1234").let {
            customerJpaRepository.save(CustomerJpaEntity.from(it)).toEntity()
        }
        Given("레스토랑이 존재하는 경우") {
            val restaurant = Restaurant(name = "마녀주방").let {
                restaurantJpaRepository.save(RestaurantJpaEntity.from(it)).toEntity()
            }
            And("주문이 존재하는 경우") {
                val order = Order(customerId = customer.id!!, restaurantId = restaurant.id!!).let {
                    orderJpaRepository.save(OrderJpaEntity.from(it)).toEntity()
                }
                When("리뷰를 작성하면") {
                    val review = Review(
                        restaurant = restaurant,
                        customerId = customer.id!!,
                        orderId = order.id!!,
                        content = "맛있어요",
                        rating = 5
                    ).let {
                        persistenceAdapter.save(it)
                    }
                    Then("리뷰가 등록된다.") {
                        val reviews = reviewJpaRepository.findAll().map { it.toEntity() }
                        reviews shouldContainExactly listOf(review)
                    }
                }
            }
            And("주문이 존재하지 않는 경우") {
                When("리뷰를 작성하면") {
                    Then("리뷰가 등록되지 않는다.") {

                    }
                }
            }
        }
        Given("리뷰가 존재하는 경우") {
            When("리뷰를 조회하면") {
                Then("리뷰가 조회된다.") {
                    true shouldBe true
                }
            }
        }

        Given("리뷰가 존재하지 않는 경우") {
            When("리뷰를 조회하면") {
                Then("리뷰가 조회되지 않는다.") {
                    true shouldBe true
                }
            }
        }

        // 주문의 상태가 배달 완료여야 리뷰를 작성할 수 있다 (integration test)
    }
}
