package com.haril.application.review.usecase

import com.haril.application.review.command.CreateReviewCommand

interface CreateReviewUsecase {

    fun createReview(command: CreateReviewCommand)

}
