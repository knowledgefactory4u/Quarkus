package com.knf.dev.demo.entity

import java.io.Serializable
import javax.persistence.*

@Table(name = "users")
@Entity
class User : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    var emailId: String? = null

    constructor(id: Long?, firstName: String?, lastName: String?, emailId: String?) : super() {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.emailId = emailId
    }

    constructor() : super() {}
}