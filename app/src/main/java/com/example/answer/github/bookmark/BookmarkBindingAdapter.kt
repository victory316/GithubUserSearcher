package com.example.answer.github.bookmark

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.example.answer.github.data.Bookmark

@BindingAdapter(value = ["bookmarks", "viewModel"])
fun setBookmarks(view: RecyclerView, items: PagedList<Bookmark>?, vm: BookmarkViewModel) {
    view.adapter?.run {
        if (this is BookmarkAdapter) this.submitList(items)
    } ?: run {
        BookmarkAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}