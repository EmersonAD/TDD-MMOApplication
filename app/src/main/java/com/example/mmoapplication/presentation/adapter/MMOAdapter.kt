package com.example.mmoapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mmoapplication.data.model.MMODomain
import com.example.mmoapplication.databinding.GameCardBinding

class MMOAdapter(
    private val games: List<MMODomain>,
    private val onClick: (website: String) -> Unit
) :
    RecyclerView.Adapter<MMOAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = GameCardBinding.inflate(view, parent, false)
        return MyViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mmoItems = games[position]
        holder.bindView(mmoItems)
    }

    override fun getItemCount(): Int = games.count()

    inner class MyViewHolder(
        private val binding: GameCardBinding,
        private val onClick: (website: String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(mmo: MMODomain) {
            binding.apply {
                settingUpCardInformation(mmo)
                btnGameSite.setOnClickListener {
                    onClick.invoke(mmo.website)
                }
                onClickReadMore()
            }
        }

        private fun GameCardBinding.settingUpCardInformation(mmo: MMODomain) {
            tvGameTitle.text = mmo.title
            tvGameDescription.text = mmo.shortDescription
            tvGameGender.text = mmo.genre
            tvGamePlataform.text = mmo.platform
            ivGameBanner.load(mmo.thumbnail)
        }

        private fun GameCardBinding.onClickReadMore() {
            imgBtnExpand.setOnClickListener {
                if (containerExpandable.visibility == View.GONE) {
                    containerExpandable.visibility = View.VISIBLE
                    imgBtnExpand.text = less
                } else {
                    containerExpandable.visibility = View.GONE
                    imgBtnExpand.text = learnMore
                }
            }
        }
    }

    companion object {
        private const val less = "Less"
        private const val learnMore = "Learn more"
    }
}