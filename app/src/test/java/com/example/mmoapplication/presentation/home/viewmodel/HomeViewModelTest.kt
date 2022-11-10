package com.example.mmoapplication.presentation.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mmoapplication.data.core.State
import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.domain.usecase.home.GetAllMMOGamesUseCase
import com.example.mmoapplication.testutil.MainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val ioDispatcher = mockk<Dispatchers>()

    private val getAllMMOGamesUseCase: GetAllMMOGamesUseCase = mockk()

    private val homeViewModel = HomeViewModel(getAllMMOGamesUseCase, ioDispatcher.IO)

    private val stateObserver = mockk<Observer<in State<List<MMODomain>>>>(relaxed = true)

    @Test
    fun `should be executed right flow when invoke success`() = runTest {
        val domainList = mockk<List<MMODomain>>()

        homeViewModel.mmoResponse.observeForever(stateObserver)

        coEvery {
            getAllMMOGamesUseCase.getGames()
        } returns domainList

        homeViewModel.getGamesList()

        verifySequence {
            stateObserver.onChanged(State.onLoading(true))
            stateObserver.onChanged(State.onSuccess(domainList))
            stateObserver.onChanged(State.onLoading(false))
        }
    }

    @Test
    fun `should throw a exception on error when invoke fail `() = runTest {

        homeViewModel.mmoResponse.observeForever(stateObserver)

        coEvery {
            getAllMMOGamesUseCase.getGames()
        } throws IOException("Error")

        homeViewModel.getGamesList()

        verifySequence {
            stateObserver.onChanged(State.onLoading(true))
            stateObserver.onChanged(State.onError(IOException("Error")))
            stateObserver.onChanged(State.onLoading(false))
        }
    }

    data class IOException(val id: String): Exception(id)
}