package com.fastcampus.intermediatebookreview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fastcampus.intermediatebookreview.databinding.ItemBookBinding
import com.fastcampus.intermediatebookreview.model.Book

class BookAdapter(val itemClickedListener: (Book) -> Unit): androidx.recyclerview.widget.ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffUtil) {

    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(bookModel: Book) {
            binding.titleTextView.text = bookModel.title
            binding.descriptionTextView.text = bookModel.description

            binding.root.setOnClickListener {
                itemClickedListener(bookModel)
            }

            Glide.with(binding.coverImageView.context).load(bookModel.coverSmallUrl).into(binding.coverImageView)

        }
    }

    //새로 생성하는 뷰홀더
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //데이터를 bind하는 부분
    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}