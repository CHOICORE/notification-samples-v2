package me.choicore.samples.domain.notification.template

import me.choicore.samples.domain.notification.template.TemplateType.CONTENT
import me.choicore.samples.domain.notification.template.TemplateType.SUBJECT_AND_CONTENT

data class TemplateDefinition(
    val type: TemplateType,
    val name: String,
    val description: String,
    val subject: String? = null,
    val content: String,
    val startWith: String = Template.DEFAULT_PLACEHOLDER_START_WITH,
    val endWith: String = Template.DEFAULT_PLACEHOLDER_END_WITH,
) {
    init {
        require(name.isNotBlank()) { "Name is required, must not be empty" }
        require(startWith.isNotBlank()) { "startWith is required, must not be empty" }
        require(endWith.isNotBlank()) { "endWith is required, must not be empty" }

        when (type) {
            SUBJECT_AND_CONTENT -> {
                requireNotNull(subject) { "Subject is required for $this" }
                require(subject.isNotBlank()) { "Subject is required for $this" }
            }

            CONTENT -> {
                require(content.isNotBlank()) { "Content is required for $this" }
            }
        }
    }
}
