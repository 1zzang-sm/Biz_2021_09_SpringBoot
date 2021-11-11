package com.callor.readbook.controller

import com.callor.readbook.config.logger
import com.callor.readbook.models.ReadBookVO
import com.callor.readbook.service.ReadBookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class ReadBookController(val readBookService: ReadBookService) {

    @RequestMapping(value=["/insert"], method=[RequestMethod.GET])
    fun insert():String {
        return "readbook/write"
    }

    @RequestMapping(value=["/insert"], method=[RequestMethod.POST])
    fun insert(readBook:ReadBookVO) :String {
        logger().debug(""">
            수신한 데이터
                {}""" , readBook.toString())
        readBookService.readBookInsert(readBook)
        logger().debug("데이터 : ", readBook.toString())
        return "redirect:/insert"
    }

    @RequestMapping(value=["/list"])
    fun list(model:Model):String {
        model["LIST"] = readBookService.readBookList()
        return "readbook/list"
    }


}