package com.haril.application.restaurant.command

class CreateRestaurantCommand(
    val name: String,
    val address: String,
    val phoneNumber: String,
    val businessHours: String,
    val menus: List<Menu>,
) {
    data class Menu(
        val name: String,
        val price: Int,
    )
}
