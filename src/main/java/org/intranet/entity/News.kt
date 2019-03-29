package org.intranet.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.PrePersist

@Entity
class News : ParentEntity() {
    var title: String? = null
    @Column(columnDefinition = "TEXT")
    var text: String? = null
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    var date: LocalDateTime? = null

    @PrePersist
    internal fun date() {
        this.date = LocalDateTime.now()
    }
}
