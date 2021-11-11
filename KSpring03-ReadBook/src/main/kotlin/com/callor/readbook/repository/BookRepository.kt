package com.callor.readbook.repository

import com.callor.readbook.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository:JpaRepository<Book, String> {
}