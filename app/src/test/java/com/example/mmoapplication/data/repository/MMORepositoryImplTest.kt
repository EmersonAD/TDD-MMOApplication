package com.example.mmoapplication.data.repository

import com.example.mmoapplication.data.model.MMOResponse
import com.example.mmoapplication.data.remote.service.MMOService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MMORepositoryImplTest {

    private val apiService = mockk<MMOService>()
    private val mmoResponse = mockk<Response<MMOResponse>>()
    private val subject = MMORepositoryImpl(apiService)


    @Test
    fun `should return correctly`() = runTest {
        //Arrange
        val expected = mmoResponse

        coEvery {
            subject.getAllGames()
        } returns mmoResponse

        //Act
        val result = subject.getAllGames()

        //Assert
        assertEquals(expected, result)
    }
}

