package com.example.mmoapplication.domain.usecase.home

import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.data.model.MMOResponse
import com.example.mmoapplication.data.model.MMOResponseItem
import com.example.mmoapplication.domain.mapper.TransformMMOResponseToDomain
import com.example.mmoapplication.domain.repository.MMORepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class GetAllMMOGamesUseCaseImplTest {

    private val repository = mockk<MMORepository>()
    private val subject = GetAllMMOGamesUseCaseImpl(repository)

    @Test
    fun `should return correctly when response is 200`() = runTest {
        //Arrange
        val response = mockk<MMOResponse>(relaxed = true)
        val expected = listOf(mockk<MMODomain>())

        coEvery {
            repository.getAllGames()
        } returns Response.success(200, response)

        mockkObject(TransformMMOResponseToDomain)
        every {
            TransformMMOResponseToDomain(response)
        } returns expected

        //Act
        val result = subject.getGames()

        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = Exception::class)
    fun `should throw a exception when response is 204`() = runTest {
        //Arrange
        val response = mockk<MMOResponse>()
        val expected = exception("No content")

        coEvery {
            repository.getAllGames()
        } returns Response.success(204, response)

        //Act
        val result = subject.getGames()

        coVerify(exactly = 0) {
            repository.getAllGames()
        }

        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = Exception::class)
    fun `should throw a exception when response is between 400 and 500`() = runTest {
        //Arrange
        val response = mockk<MMOResponse>()
        val expected = exception()

        coEvery {
            repository.getAllGames()
        } returns Response.success(404, response)

        //Act
        val result = subject.getGames()

        coVerify(exactly = 0) {
            repository.getAllGames()
        }

        //Assert
        assertEquals(expected, result)
    }

    @Test(expected = Exception::class)
    fun `should throw a exception when got else branch`() = runTest {
        //Arrange
        val expected = exception()

        coEvery {
            repository.getAllGames()
        } throws Exception()

        //Act
        val result = subject.getGames()

        coVerify(exactly = 0) {
            repository.getAllGames()
        }

        //Assert
        assertEquals(expected, result)
    }

    private fun exception(message: String? = null) = Exception(message)
}