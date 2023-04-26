package com.haril.application.review.service

import com.haril.application.review.command.CreateReviewCommand
import com.haril.domain.customer.entity.Customer
import com.haril.domain.customer.repository.CustomerRepository
import com.haril.domain.order.entity.Order
import com.haril.domain.order.repository.OrderRepository
import com.haril.domain.restaurant.entity.Restaurant
import com.haril.domain.restaurant.repository.RestaurantRepository
import com.haril.domain.review.repository.ReviewRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class CreateReviewServiceTest : FreeSpec({

    val reviewRepository = mockk<ReviewRepository>(relaxed = true)
    val orderRepository = mockk<OrderRepository>()
    val restaurantRepository = mockk<RestaurantRepository>()
    val customerRepository = mockk<CustomerRepository>()
    val createReviewService =
        CreateReviewService(reviewRepository, orderRepository, restaurantRepository, customerRepository)

    val customer = Customer(id = 1L, name = "John", address = "서울", phoneNumber = "010-1234-5678")
    every { customerRepository.findById(any()) } returns customer
    every { restaurantRepository.findById(any()) } returns Restaurant(1L, "마법사주방")
    "Customer 가 레스토랑에 주문을 한 적이 있다면" - {
        val order = Order(id = 1L, customerId = 1L, restaurantId = 1L)
        every { orderRepository.findById(any()) } returns order
        "해당 주문에 대한 리뷰를 작성할 수 있다." {
            val createReviewCommand = CreateReviewCommand(
                customerId = order.customerId,
                restaurantId = order.restaurantId,
                orderId = order.id!!,
                content = "맛있어요",
                rating = 5,
            )
            createReviewService.createReview(createReviewCommand)
            verify(exactly = 1) { reviewRepository.save(any()) }
        }
    }

    "Customer 가 레스토랑에 주문을 한 적이 없다면" - {
        every { orderRepository.findById(any()) } throws NoSuchElementException()
        "해당 주문에 대한 리뷰를 작성할 수 없다." {
            shouldThrow<NoSuchElementException> {
                val createReviewCommand = CreateReviewCommand(
                    customerId = 1L,
                    restaurantId = 1L,
                    orderId = 1L,
                    content = "맛있어요",
                    rating = 5,
                )
                createReviewService.createReview(createReviewCommand)
            }
        }
    }

    "Customer 가 레스토랑에 주문을 한 적이 있지만" - {
        val order = Order(id = 1L, customerId = 1L, restaurantId = 1L)
        every { orderRepository.findById(any()) } returns order
        "해당 주문에 대한 리뷰를 작성한 적이 있다면" - {
            every { reviewRepository.existsByOrderId(any()) } returns true
            "해당 주문에 대한 리뷰를 작성할 수 없다." {
                shouldThrow<IllegalStateException> {
                    val createReviewCommand = CreateReviewCommand(
                        customerId = order.customerId,
                        restaurantId = order.restaurantId,
                        orderId = order.id!!,
                        content = "맛있어요",
                        rating = 5,
                    )
                    createReviewService.createReview(createReviewCommand)
                }
            }
        }
    }

})
