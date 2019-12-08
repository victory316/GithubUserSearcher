package com.example.answer.github.bookmark

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.answer.github.data.Bookmark
import com.example.answer.github.data.BookmarkDao

class BookmarkViewModel(private val dao: BookmarkDao) : BaseViewModel() {
    val items: LiveData<PagedList<Bookmark>> = LivePagedListBuilder(dao.findAll(),  /* page size */ 10).build()

    fun delete(bookmark: Bookmark) = ioThread { dao.delete(bookmark) }
}