package com.callor.readbook.service

import com.callor.readbook.model.Book

interface ReadBookService {

    fun selectAll(): Array<Book>

    fun insert(book:Book): Book
    fun update(book:Book): Book
    fun delete(seq: Long)
}