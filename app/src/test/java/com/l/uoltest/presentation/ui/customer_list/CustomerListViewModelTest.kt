package com.l.uoltest.presentation.ui.customer_list

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.l.uoltest.CoroutineTestRule
import com.l.uoltest.data.model.Result
import com.l.uoltest.repository.FakeCustomerRepository
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
class CustomerListViewModelTest {

    @get:Rule
    val rule = CoroutineTestRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val repository = FakeCustomerRepository()
    private lateinit var viewModel: CustomerListViewModel

    @Before
    fun setUp() {
        viewModel = CustomerListViewModel(repository, testDispatcher)
    }

    @Test
    fun `on get API SUCCESS, emit Result_Success`() = runBlocking {
        viewModel.customer.test {
            assertThat(awaitItem())
                .isInstanceOf(Result.Success::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `on get API ERROR, emit Result_Error`() = runBlocking {
        repository.setReturnError(true)
        viewModel = CustomerListViewModel(repository, testDispatcher)

        viewModel.customer.test {
            assertThat(awaitItem())
                .isInstanceOf(Result.Error::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }
}