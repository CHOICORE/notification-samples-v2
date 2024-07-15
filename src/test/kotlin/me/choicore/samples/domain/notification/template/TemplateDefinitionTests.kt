package me.choicore.samples.domain.notification.template

import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TemplateDefinitionTests {
    @Test
    fun t1() {
        assertThatThrownBy {
            TemplateDefinition(
                type = TemplateType.SUBJECT_AND_CONTENT,
                name = "welcome-template",
                description = "Welcome template",
                subject = "",
                content = "#{content}",
                startWith = "#{",
                endWith = "}",
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun t2() {
        assertThatThrownBy {
            TemplateDefinition(
                type = TemplateType.SUBJECT_AND_CONTENT,
                name = "welcome-template",
                description = "Welcome template",
                subject = null,
                content = "#{content}",
                startWith = "#{",
                endWith = "}",
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun t3() {
        assertThatNoException().isThrownBy {
            TemplateDefinition(
                type = TemplateType.CONTENT,
                name = "welcome-template",
                description = "Welcome template",
                content = "#{content}",
                startWith = "#{",
                endWith = "}",
            )
        }
    }
}
