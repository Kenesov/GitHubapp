package com.example.githubapp.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.databinding.ItemUserBinding
import com.example.githubapp.models.Data.ItemsData

class UsernameSearchAdapter: ListAdapter<ItemsData, UsernameSearchAdapter.UsernameViewHolder>(diffCallback) {
    inner class UsernameViewHolder(private val binding: ItemUserBinding):
            ViewHolder(binding.root){
                fun bind(){
                    val d = getItem(adapterPosition)
                    binding.apply {
                        Glide.with(profileImgSearch)
                            .load(d.avatar_url)
                            .into(profileImgSearch)
                    }

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsernameViewHolder {
        return UsernameViewHolder(
            ItemUserBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_user, parent, false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: UsernameViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallback: DiffUtil.ItemCallback<ItemsData>(){
        override fun areItemsTheSame(oldItem: ItemsData, newItem: ItemsData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemsData, newItem: ItemsData): Boolean {
            return oldItem == newItem
        }
    }
}