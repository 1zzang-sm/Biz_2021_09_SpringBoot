package com.callor.readbook.repository

import com.callor.readbook.model.ReadBook
import org.springframework.data.jpa.repository.JpaRepository

interface ReadBookRepository:JpaRepository<ReadBook, Long> {
}