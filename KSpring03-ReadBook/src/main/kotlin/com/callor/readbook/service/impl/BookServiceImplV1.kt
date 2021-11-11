package com.callor.readbook.service.impl

import com.callor.readbook.model.Book
import com.callor.readbook.repository.BookRepository
import com.callor.readbook.service.BookService
import org.springframework.stereotype.Service

@Service("bServiceV1")
class BookServiceImplV1(val BookRepo:BookRepository): BookService {
    override fun selectAll(): Array<Book> {
        TODO("Not yet implemented")
    }

    override fun insert(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun update(book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun delete(ISBN: String) {
        TODO("Not yet implemented")
    }

}