package com.example.githubapp.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.githubapp.R
import com.example.githubapp.databinding.ItemProfileRepoBinding
import com.example.githubapp.databinding.ItemProfileRepoBinding.bind
import com.example.githubapp.models.Data.GetUserRepositoriesData

class ProfileRepositoryAdapter:ListAdapter<GetUserRepositoriesData, ProfileRepositoryAdapter.RepoProfileViewHolder>(diffCallback) {
    inner class RepoProfileViewHolder(private val binding: ItemProfileRepoBinding):
            ViewHolder(binding.root) {
                fun bind(){

                    val d = getItem(adapterPosition)

                    binding.apply {
                        itemTextProjectProf.text = d.name
                    }

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoProfileViewHolder {
      return   RepoProfileViewHolder(
          bind(LayoutInflater.from(
              parent.context
          ).inflate(
              R.layout.item_profile_repo, parent , false
          ))
      )
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

    override fun onBindViewHolder(holder: RepoProfileViewHolder, position: Int) {
        holder.bind()
    }
}