package com.knf.dev.demo.service
import com.knf.dev.demo.entity.User
import javax.inject.Inject
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Singleton
class UserService {
    @Inject
    var entityManager: EntityManager? = null

    fun getUsers(): kotlin.collections.List<User?>? {
        return entityManager!!.
          createQuery("SELECT c FROM User c").
           resultList as List<User?>?
    }

    fun getUser(id: Long?): User {
        return entityManager!!.find(User::class.java, id)
    }

    @Transactional(Transactional.TxType.REQUIRED)
    fun addUser(user: User?): User? {
        entityManager!!.persist(user)
        return user
    }

    @Transactional(Transactional.TxType.REQUIRED)
    fun updateUser(id: Long?, user: User) {
        val userToUpdate: User = entityManager!!.
           find(User::class.java, id)
        if (null != userToUpdate) {
            userToUpdate.firstName = user.firstName
            userToUpdate.lastName = user.lastName
            userToUpdate.emailId = user.emailId
        } else {
            throw RuntimeException("No such user available")
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    fun deleteUser(id: Long?) {
        val user: User = getUser(id)
        entityManager!!.remove(user)
    }
}