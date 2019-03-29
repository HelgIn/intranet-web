package org.intranet.repositories

import org.intranet.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional


interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?

    @Transactional
    @Modifying
    @Query("UPDATE users u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email WHERE u.username = :username")
    fun updateFirstName(@Param("username") username: String,
                        @Param("firstName") firstName: String,
                        @Param("lastName") lastName: String,
                        @Param("email") email: String): Int

}
