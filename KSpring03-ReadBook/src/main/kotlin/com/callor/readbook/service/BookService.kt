package com.callor.readbook.service

import com.callor.readbook.model.Book

interface BookService {

    fun selectAll(): Array<Book>

    fun insert(book:Book): Book
    fun update(book:Book): Book
    fun delete(ISBN:String)
}