package me.choicore.samples.infrastructure.jpa.notification.template.adapter

import me.choicore.samples.domain.notification.template.Template
import me.choicore.samples.domain.notification.template.TemplateDefinition
import me.choicore.samples.domain.notification.template.TemplateRepository
import me.choicore.samples.infrastructure.jpa.notification.template.TemplateEntity
import me.choicore.samples.infrastructure.jpa.notification.template.TemplateJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DefaultTemplateRepository(
    private val templateJpaRepository: TemplateJpaRepository,
) : TemplateRepository {
    override fun register(definition: TemplateDefinition): Long = templateJpaRepository.save(TemplateEntity(definition)).id

    override fun existsByName(name: String): Boolean = templateJpaRepository.existsByName(name)

    override fun getTemplate(name: String): Template =
        templateJpaRepository.findByName(name)?.toTemplate() ?: throw IllegalStateException("Template not found")
}
