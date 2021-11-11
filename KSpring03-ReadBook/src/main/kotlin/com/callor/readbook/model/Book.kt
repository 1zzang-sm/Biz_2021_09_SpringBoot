package com.callor.readbook.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "tbl_book", schema = "myDB")
data class Book(
        @Id
        @Column(columnDefinition = "CHAR(10)")
        var ISBN:String ? = null,

        @Column(columnDefinition = "VARCHAR(25)", nullable = false)
        var title:String ? = null,

        @Column(columnDefinition = "VARCHAR(25)", nullable = false)
        var comp:String ? = null,

        @Column(columnDefinition = "VARCHAR(25)", nullable = false)
        var author:String ? = null,

        @Column(nullable = false)
        var price:Int ? = null
)
