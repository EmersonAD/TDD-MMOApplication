package com.example.mmoapplication.domain.usecase.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.data.model.MMOResponse
import com.example.mmoapplication.domain.mapper.TransformMMOResponseToDomain
import com.example.mmoapplication.domain.repository.MMORepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class GetAllMMOGamesUseCaseImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = mockk<MMORepository>()
    private val subject = GetAllMMOGamesUseCaseImpl(repository)

    @Test
    fun `should return correctly when response is ok`() = runTest {
        //Arrange
        val response = mockk<MMOResponse>()
        val expected = listOf(mockk<MMODomain>())

        coEvery {
            repository.getAllGames()
        } returns response

        mockkObject(TransformMMOResponseToDomain)
        every {
            TransformMMOResponseToDomain.init(response)
        } returns expected

        //Act
        val result = subject.getGames()

        coVerify(exactly = 1) {
            repository.getAllGames()
        }

        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = Exception::class)
    fun `should throw a exception when call fail`() = runTest {
        //Arrange
        val expected = exception

        coEvery {
            repository.getAllGames()
        } throws exception

        //Act
        val result = subject.getGames()

        coVerify(exactly = 0) {
            repository.getAllGames()
        }

        //Assert
        assertEquals(expected, result)
    }

    private val exception = IllegalArgumentException("Api response on failure")
}