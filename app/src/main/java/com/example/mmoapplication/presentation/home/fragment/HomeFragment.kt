package com.example.mmoapplication.presentation.home.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.mmoapplication.data.core.Status
import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.data.remote.retrofit.ApiService
import com.example.mmoapplication.data.repository.MMORepositoryImpl
import com.example.mmoapplication.databinding.FragmentHomeBinding
import com.example.mmoapplication.domain.usecase.home.GetAllMMOGamesUseCaseImpl
import com.example.mmoapplication.presentation.adapter.MMOAdapter
import com.example.mmoapplication.presentation.home.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var repository: MMORepositoryImpl
    private lateinit var useCaseImpl: GetAllMMOGamesUseCaseImpl
    private lateinit var mAdapter: MMOAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = MMORepositoryImpl(ApiService.initRetrofit)
        useCaseImpl = GetAllMMOGamesUseCaseImpl(repository)
        viewModel = HomeViewModel(useCaseImpl, Dispatchers.IO)

        observeVMEvents()
        getAllGames()
    }

    private fun observeVMEvents() {
        viewModel.mmoResponse.observe(viewLifecycleOwner) {
            if (viewLifecycleOwner.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when (it.Status) {
                Status.SUCCESS -> {
                    it.data?.let { games ->
                        setRecycler(games)
                    }
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
    }

    private fun getAllGames() {
        viewModel.getGamesList()
    }

    private fun setRecycler(list: List<MMODomain>) {
        mAdapter = MMOAdapter(list) { website ->
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(website)))
        }
        binding.rvGameList.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}