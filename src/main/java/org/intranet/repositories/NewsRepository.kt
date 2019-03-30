package org.intranet.repositories

import org.intranet.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface NewsRepository : JpaRepository<Post, Long> {
    fun findAllByOrderByDateDesc(): List<Post>
}
