package com.example.githubapp.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.databinding.ItmeRepositoryBinding
import com.example.githubapp.models.Data.GetUserRepositoriesData

class RepositoryAdapter: ListAdapter<GetUserRepositoriesData, RepositoryAdapter.RepositoryViewHolder>(diffCallback) {

    inner class RepositoryViewHolder(private val binding: ItmeRepositoryBinding): ViewHolder(binding.root){

        fun bind(){

            val d = getItem(adapterPosition)

            binding.apply {
                Glide.with(profileImgItem)
                    .load(d.owner.avatar_url)
                    .into(profileImgItem)

                textNameItem.text = d.name
                textUsernameItem.text = d.owner.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItmeRepositoryBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.itme_repository, parent, false))
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind()
    }
    private object diffCallback : DiffUtil.ItemCallback<GetUserRepositoriesData>(){
        override fun areItemsTheSame(
            oldItem: GetUserRepositoriesData,
            newItem: GetUserRepositoriesData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetUserRepositoriesData,
            newItem: GetUserRepositoriesData
        ): Boolean {
            return oldItem == newItem
        }
    }
}