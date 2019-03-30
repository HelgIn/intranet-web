package org.intranet.services

import org.intranet.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun mapUserToUserDetails(user: User): UserDetails {
        return org.springframework.security.core.userdetails.User(user.username, passwordEncoder.encode(user.password), listOf(SimpleGrantedAuthority("ROLE_USER")))
    }

}
