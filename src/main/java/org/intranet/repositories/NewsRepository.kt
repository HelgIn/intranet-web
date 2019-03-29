package org.intranet.repositories

import org.intranet.entity.News
import org.springframework.data.jpa.repository.JpaRepository

interface NewsRepository : JpaRepository<News, Long> {
    fun findAllByOrderByDateDesc(): List<News>
}
