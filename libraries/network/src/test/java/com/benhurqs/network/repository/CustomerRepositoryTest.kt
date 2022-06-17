package com.benhurqs.network.repository

import com.benhurqs.network.CustomerAPI
import com.benhurqs.network.ErrorType
import com.benhurqs.network.model.CustomerListResponse
import com.benhurqs.network.model.CustomerResponse
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CustomerRepositoryTest{

    @MockK
    lateinit var protocolMock: CustomerProtocol

    @MockK
    lateinit var customerAPI: CustomerAPI

    @MockK
    lateinit var errorType: ErrorType


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        CustomerRepository.getCustomerList(customerAPI, protocolMock)
    }

    @Test
    fun `check if api is called`(){
        verify { customerAPI.getCustomer(any(), any(), any()) }
        confirmVerified(customerAPI)
    }

    @Test
    fun `check onLoading is called`(){
        CustomerRepository.onLoading()
        verify { protocolMock.showLoading() }
        confirmVerified(protocolMock)
    }

    @Test
    fun `check onError is called`(){
        CustomerRepository.onError(errorType)
        verify { protocolMock.showError(errorType) }
        confirmVerified(protocolMock)
    }

    @Test
    fun `check onError is called when list is null`(){
        CustomerRepository.onSuccess(null)
        verify { protocolMock.showError(ErrorType.EMPTY) }
        verify(exactly = 0) { protocolMock.loadList(any()) }
        confirmVerified(protocolMock)
    }

    @Test
    fun `check onError is called when customers is null`(){
        val list = CustomerListResponse().apply {
            this.customers = null
        }

        CustomerRepository.onSuccess(list)
        verify { protocolMock.showError(ErrorType.EMPTY) }
        verify(exactly = 0) { protocolMock.loadList(any()) }
        confirmVerified(protocolMock)
    }

    @Test
    fun `check onError is called when customers is empty`(){
        val list = CustomerListResponse().apply {
            this.customers = ArrayList<CustomerResponse>()
        }

        CustomerRepository.onSuccess(list)
        verify { protocolMock.showError(ErrorType.EMPTY) }
        verify(exactly = 0) { protocolMock.loadList(any()) }
        confirmVerified(protocolMock)
    }

    @Test
    fun `check LoadList is called when customers is not empty`(){
        val list = CustomerListResponse().apply {
            this.customers = ArrayList<CustomerResponse>().apply {
                add(CustomerResponse())
            }
        }

        CustomerRepository.onSuccess(list)
        verify(exactly = 0) { protocolMock.showError(ErrorType.EMPTY) }
        verify { protocolMock.loadList(list.customers!!) }
        confirmVerified(protocolMock)
    }


}