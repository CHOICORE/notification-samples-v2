package me.choicore.samples.infrastructure.jpa.notification.template

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import me.choicore.samples.domain.notification.template.Template
import me.choicore.samples.domain.notification.template.TemplateDefinition
import me.choicore.samples.domain.notification.template.TemplateType
import me.choicore.samples.infrastructure.jpa.PrimaryKey
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "notification_template")
class TemplateEntity(
    @Enumerated(EnumType.STRING)
    val type: TemplateType,
    val name: String,
    val description: String,
    subject: String? = null,
    content: String,
    val startWith: String,
    val endWith: String,
) : PrimaryKey() {
    var content: String = content
        protected set

    var subject: String? = subject

    fun toTemplate(): Template =
        Template(
            id = id,
            type = type,
            name = name,
            description = description,
            subject = subject,
            content = content,
            startWith = startWith,
            endWith = endWith,
        )

    constructor(definition: TemplateDefinition) : this(
        type = definition.type,
        name = definition.name,
        description = definition.description,
        subject = definition.subject,
        content = definition.content,
        startWith = definition.startWith,
        endWith = definition.endWith,
    )

    @CreatedDate
    var registeredAt: LocalDateTime = LocalDateTime.now()
        private set(value) {
            field = value
            lastModifiedAt = value
        }

    @LastModifiedDate
    var lastModifiedAt: LocalDateTime = registeredAt
        private set
}
