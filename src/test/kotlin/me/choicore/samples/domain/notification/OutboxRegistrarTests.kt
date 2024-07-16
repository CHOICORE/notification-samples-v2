package me.choicore.samples.domain.notification

import me.choicore.samples.domain.notification.template.PlaceholderRegistry
import me.choicore.samples.domain.notification.template.TemplateDefinition
import me.choicore.samples.domain.notification.template.TemplateRegistry
import me.choicore.samples.domain.notification.template.TemplateType
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
@Rollback(value = false)
class OutboxRegistrarTests(
    private val outboxRegistrar: OutboxRegistrar,
    private val templateRegistry: TemplateRegistry,
) {
    @Test
    fun t1() {
        val definition =
            TemplateDefinition(
                type = TemplateType.CONTENT,
                name = "welcome-push-template",
                description = "Welcome template",
                content = "Hello, {{name}}!, How are you? {{name}}!",
                startWith = "{{",
                endWith = "}}",
            )

        templateRegistry.register(definition)

        outboxRegistrar.register(
            notificationType = NotificationType.PUSH,
            dispatchStrategy = DispatchStrategy.Scheduled(LocalDateTime.now().plusDays(1)),
            templateName = "welcome-push-template",
            placeholderRegistry =
                PlaceholderRegistry().apply {
                    addPlaceholder("name", "world")
                },
            recipient = Recipient(fullName = "John Doe", emailAddress = "core@abc.com", phoneNumber = "1234567890"),
        )

        // TODO: Add assertions
    }
}
