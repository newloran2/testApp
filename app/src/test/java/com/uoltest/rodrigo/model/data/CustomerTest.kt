package com.uoltest.rodrigo.model.data

import org.junit.Assert
import org.junit.Test

class CustomerTest {

    @Test
    fun `should return true when all required properties are not empty`() {
        val customer =
            Customer(name = "Fulano", id = "123", status = "active", email = "fulano@gmail.com")

        val result = customer.requiredProperties()

        Assert.assertTrue(result)
    }

    @Test
    fun `should return false when name is empty`() {
        val customer = Customer(id = "123", status = "active", email = "fulano@gmail.com")

        val result = customer.requiredProperties()

        Assert.assertFalse(result)
    }

    @Test
    fun `should return false when id is empty`() {
        val customer = Customer(name = "Fulano", status = "active", email = "fulano@gmail.com")

        val result = customer.requiredProperties()

        Assert.assertFalse(result)
    }

    @Test
    fun `should return false when status is empty`() {
        val customer = Customer(name = "Fulano", id = "123", email = "fulano@gmail.com")

        val result = customer.requiredProperties()

        Assert.assertFalse(result)
    }

    @Test
    fun `should return false when email is empty`() {
        val customer = Customer(name = "Fulano", id = "123", status = "active")

        val result = customer.requiredProperties()

        Assert.assertFalse(result)
    }
}