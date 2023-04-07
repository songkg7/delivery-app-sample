package com.haril.infrastructure.customer.entity

import jakarta.persistence.*

@Entity
@Table(name = "customer")
class CustomerJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L, // TODO: id 를 초기화해놔도 JPA 가 처리해줄 수 있는지 검증
    val name: String,
    val address: String,
    val phoneNumber: String,
) {
}
