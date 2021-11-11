package com.callor.readbook.service.impl

import com.callor.readbook.model.Book
import com.callor.readbook.repository.ReadBookRepository
import com.callor.readbook.service.ReadBookService
import org.springframework.stereotype.Service

@Service("rServiceV1")
class ReadBookServiceImplV1(val ReadRepo:ReadBookRepository): ReadBookService {
    override fun selectAll(): Array<Book> {
        TODO("Not yet implemented")
    }

    override fun insert(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun update(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun delete(seq: Long) {
        TODO("Not yet implemented")
    }
}