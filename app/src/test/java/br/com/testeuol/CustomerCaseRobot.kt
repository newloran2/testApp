package br.com.testeuol

import br.com.testeuol.data.model.Customers
import br.com.testeuol.data.repository.CustomerRepository
import br.com.testeuol.feature.home.usecase.CustomerUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert


fun withRobot(lb: CustomerCaseRobot.() -> Unit): CustomerCaseRobot =
    CustomerCaseRobot().apply(lb)

class CustomerCaseRobot {

    @MockK(relaxUnitFun = true)
    lateinit var mockCustomerRepository: CustomerRepository

    @MockK(relaxUnitFun = true)
    lateinit var customers : Customers

    var customerUseCase: CustomerUseCase


    init {
        MockKAnnotations.init(this)
        customerUseCase = CustomerUseCase(mockCustomerRepository)
    }


    infix fun launch(lb: ActionCustomerRobot.() -> Unit) =
        ActionCustomerRobot(this).apply(lb)

    fun andWhenGetRepositoryCustomerReturnsSuccess() {
        coEvery { mockCustomerRepository.getCustomers() } returns customers
    }
}

class ActionCustomerRobot(private val robot: CustomerCaseRobot) {


    infix fun check(lb: ResultCustomerRobot.() -> Unit) =
        ResultCustomerRobot(robot).apply(lb)

    fun getCustomers() {


        runBlocking {
            robot.customerUseCase.invoke()
        }

    }

}

class ResultCustomerRobot(private val robot: CustomerCaseRobot) {

    fun thatGetRepositoryCustomerSuccess() {
        Assert.assertNotNull(robot.customers)
    }

}