package com.example.retrofit_gita.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_gita.R
import com.example.retrofit_gita.data.network.models.response.BookResponse
import com.example.retrofit_gita.databinding.ItemBookBinding


class BookAdapter : ListAdapter<BookResponse, BookAdapter.BookViewHolder>(BookDiffUtil) {
    private var clickItemBookListener: ((Int) -> Unit)? = null

    fun setClickListener(listener: (Int) -> Unit) {
        clickItemBookListener = listener
    }

    object BookDiffUtil : DiffUtil.ItemCallback<BookResponse>() {
        override fun areItemsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookResponse, newItem: BookResponse): Boolean {
            return oldItem == newItem
        }

    }

    inner class BookViewHolder(binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageBook = binding.ivItemBook
        private val titleBook = binding.tvItemTitleBook
        private val authorBook = binding.tvItemAuthor
        private val countPage = binding.tvCountPage

        init {
            itemView.setOnClickListener {
                clickItemBookListener?.invoke(bindingAdapterPosition)
            }
        }

        fun bind(data: BookResponse) {
            Glide.with(imageBook).load(R.drawable.image_book).into(imageBook)
            titleBook.text = data.title
            authorBook.text = data.author
            countPage.text = data.pageCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemBookBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}