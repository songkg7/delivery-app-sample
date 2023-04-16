package com.haril.application.review.service

import com.haril.domain.customer.entity.Customer
import com.haril.domain.review.repository.ReviewRepository
import io.kotest.core.spec.style.FreeSpec
import io.mockk.mockk

class CreateReviewServiceTest : FreeSpec({

//    val reviewRepository = mockk<ReviewRepository>()
//    val createReviewService = CreateReviewService(reviewRepository)

    "Customer 가 레스토랑에 주문을 한 적이 있다면" - {
        Customer(name = "John", address = "서울", phoneNumber = "010-1234-5678")
        // find order by customer id
        "해당 주문에 대한 리뷰를 작성할 수 있다." - {

        }
    }

})
