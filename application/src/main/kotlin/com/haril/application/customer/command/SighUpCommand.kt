package com.haril.application.customer.command

data class SighUpCommand(
    val name: String,
    val address: String,
    val phoneNumber: String,
)
