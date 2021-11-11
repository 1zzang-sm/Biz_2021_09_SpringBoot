package com.callor.readbook.service

import com.callor.readbook.models.ReadBookDTO
import com.callor.readbook.models.ReadBookVO

interface ReadBookService {

    fun readBookInsert(readBook: ReadBookVO)
    fun readBookList():Array<ReadBookDTO>
}