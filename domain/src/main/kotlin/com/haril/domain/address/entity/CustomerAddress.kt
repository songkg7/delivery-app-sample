package com.haril.domain.address.entity

class CustomerAddress(
    val id: Long? = null,
    val city: String,
    val street: String,
    val zipcode: String,
) {

    override fun toString(): String {
        return "CustomerAddress(id=$id, city='$city', street='$street', zipcode='$zipcode')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CustomerAddress

        if (city != other.city) return false
        if (street != other.street) return false
        return zipcode == other.zipcode
    }

    override fun hashCode(): Int {
        var result = city.hashCode()
        result = 31 * result + street.hashCode()
        result = 31 * result + zipcode.hashCode()
        return result
    }

}
