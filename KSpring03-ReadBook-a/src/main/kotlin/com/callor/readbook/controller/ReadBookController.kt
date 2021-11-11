package com.callor.readbook.controller

import com.callor.readbook.config.logger
import com.callor.readbook.models.ReadBookDTO
import com.callor.readbook.repository.BookRepository
import com.callor.readbook.repository.ReadBookRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
class ReadBookController(val readBookDao: ReadBookRepository,
                         val bookDao:BookRepository) {

    @RequestMapping(value=["/", ""], method=[RequestMethod.GET])
    fun home(): String {
        return "redirect:/insert"
    }

    @RequestMapping(value=["/insert"], method=[RequestMethod.GET])
    fun insert(): String {
        return "readbook/write.html"
    }

    @RequestMapping(value=["/insert"], method=[RequestMethod.POST])
    fun insert(readBook:ReadBookDTO): String {
        logger().debug(""">
            |수신한 데이터
            |{}""", ReadBookDTO.)
        val readBook = ReadBookDTO(
                isbn = readBook.isbn,
                title = readBook.title,
                content = readBook.content
        )
        val bookDTO = BookDTO(
                isbn = readBook.isbn,
                title = readBook.title,

                )

        readBookDao.save(readBookDTO)
        bookDao.save(bookDTO)
        return "redirect:/insert"


    }
    @RequestMapping(value = ["/delete"], method = [RequestMethod.POST])
    fun delete(readBook: ReadBookDTO):String{

    }
}