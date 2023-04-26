package com.haril.application.review.service

import com.haril.application.review.command.CreateReviewCommand
import com.haril.application.review.usecase.CreateReviewUsecase
import com.haril.domain.customer.repository.CustomerRepository
import com.haril.domain.order.repository.OrderRepository
import com.haril.domain.restaurant.repository.RestaurantRepository
import com.haril.domain.review.entity.Review
import com.haril.domain.review.repository.ReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CreateReviewService(
    private val reviewRepository: ReviewRepository,
    private val orderRepository: OrderRepository,
    private val restaurantRepository: RestaurantRepository,
    private val customerRepository: CustomerRepository,
) : CreateReviewUsecase {

    override fun createReview(command: CreateReviewCommand) {
        check(reviewRepository.existsByOrderId(command.orderId).not()) { "이미 리뷰가 존재합니다." }
        val customer = customerRepository.findById(command.customerId)
        val order = orderRepository.findById(command.orderId)
        val restaurant = restaurantRepository.findById(command.restaurantId)
        Review(
            customerId = customer.id!!,
            restaurant = restaurant,
            orderId = order.id!!,
            content = command.content,
            rating = command.rating,
        ).let {
            reviewRepository.save(it)
        }
    }

}
