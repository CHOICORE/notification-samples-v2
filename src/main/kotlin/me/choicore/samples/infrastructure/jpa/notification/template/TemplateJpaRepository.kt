package me.choicore.samples.infrastructure.jpa.notification.template

import org.springframework.data.jpa.repository.JpaRepository

interface TemplateJpaRepository : JpaRepository<TemplateEntity, Long> {
    fun findByName(name: String): TemplateEntity?

    fun existsByName(name: String): Boolean
}
