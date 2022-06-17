package com.benhurqs.network.model

import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test

class CustomerResponseTest {

    lateinit var customerResponse: CustomerResponse
    private val empty = "---"
    private val nameMock = "NAME"
    private val emailMock = "EMAIL"
    private val phoneMock = "PHONE"

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `check if name is empty`(){
        customerResponse = CustomerResponse()
        assert(customerResponse.name == empty)
    }

    @Test
    fun `check if name is not empty`(){
        customerResponse = CustomerResponse().apply {
            name = nameMock
        }
        assert(customerResponse.name == nameMock)
    }

    @Test
    fun `check if email is empty`(){
        customerResponse = CustomerResponse()
        assert(customerResponse.email == empty)
    }

    @Test
    fun `check if email is not empty`(){
        customerResponse = CustomerResponse().apply {
            email = emailMock
        }
        assert(customerResponse.email == emailMock)
    }

    @Test
    fun `check if phone is empty`(){
        customerResponse = CustomerResponse()
        assert(customerResponse.email == empty)
    }

    @Test
    fun `check if phone is not empty`(){
        customerResponse = CustomerResponse().apply {
            phone = phoneMock
        }
        assert(customerResponse.phone == phoneMock)
    }
}

