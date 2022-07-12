package br.com.testeuol

import org.junit.Test

class CustomerCaseTest {

    @Test
    fun `get repository tag with success`() {

        withRobot {
            andWhenGetRepositoryCustomerReturnsSuccess()
        } launch {
            getCustomers()
        }check {
            thatGetRepositoryCustomerSuccess()
        }

    }
}