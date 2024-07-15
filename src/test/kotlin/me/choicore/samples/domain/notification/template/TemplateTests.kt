package me.choicore.samples.domain.notification.template

import me.choicore.samples.domain.notification.Message
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TemplateTests {
    @Test
    fun t1() {
        val template =
            Template(
                id = 1,
                type = TemplateType.SUBJECT_AND_CONTENT,
                name = "welcome-template",
                description = "Welcome template",
                subject = "welcome to the world of #{name}",
                content = "Hello, #{name}!, How are you? #{name}!",
                startWith = "#{",
                endWith = "}",
            )

        val registry: PlaceholderRegistry =
            PlaceholderRegistry().apply {
                addPlaceholder("name", "world")
                addPlaceholder("user", "world")
            }

        val message: Message = template.render(registry)
        assertThat(template.hasPlaceholders()).isTrue
        assertThat("Hello, world!, How are you? world!").isEqualTo(message.content)
        assertThat("welcome to the world of world").isEqualTo(message.subject)
    }
}
