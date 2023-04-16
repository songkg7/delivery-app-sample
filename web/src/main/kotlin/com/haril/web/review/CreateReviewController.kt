package com.haril.web.review

import com.haril.application.review.command.CreateReviewCommand
import com.haril.application.review.usecase.CreateReviewUsecase
import com.haril.web.review.request.CreateReviewRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateReviewController(
    private val createReviewUsecase: CreateReviewUsecase,
) {

    @PostMapping("/review")
    fun createReview(@RequestBody request: CreateReviewRequest): ResponseEntity<Void> {
        createReviewUsecase.createReview(CreateReviewCommand(
            customerId = request.customerId,
            restaurantId = request.restaurantId,
            orderId = request.orderId,
            content = request.content,
            rating = request.rating,
        ))
        return ResponseEntity.ok().build()
    }

}
