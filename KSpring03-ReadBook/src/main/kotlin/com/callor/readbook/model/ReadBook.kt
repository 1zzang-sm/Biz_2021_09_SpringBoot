package com.callor.readbook.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_readbook", schema="myDB")
data class ReadBook(
        @Id
        @Column(columnDefinition = "BIGINT")
        var seq:Int? = null,

        @Column(columnDefinition = "VARCHAR(25)", nullable = false)
        var ISBN:String? = null,

        @Column(nullable = false)
        var rdate:Int? = null,

        @Column(nullable = false)
        var sTime:Int? = null,

        @Column(nullable = false)
        var etime:Int? = null,

        @Column(columnDefinition = "VARCHAR(125)", nullable = true)
        var rating:String? = null,

        @Column(columnDefinition = "VARCHAR(125)", nullable = true)
        var comment:String? = null
)
