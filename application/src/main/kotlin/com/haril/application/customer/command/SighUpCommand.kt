package com.haril.application.customer.command

data class SighUpCommand(
    val name: String,
    val phoneNumber: String,
    val addresses: List<AddressCommand>,
)
