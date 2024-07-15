package me.choicore.samples.domain.notification

import me.choicore.samples.domain.notification.template.PlaceholderRegistry
import me.choicore.samples.domain.notification.template.TemplateDefinition
import me.choicore.samples.domain.notification.template.TemplateRegistry
import me.choicore.samples.domain.notification.template.TemplateType
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class NotificationManagerTests(
    private val notificationManager: NotificationManager,
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

        notificationManager.append(
            type = NotificationType.PUSH,
            templateName = "welcome-push-template",
            placeholderRegistry =
                PlaceholderRegistry().apply {
                    addPlaceholder("name2", "world")
                },
            recipient = Recipient(fullName = "John Doe", emailAddress = "core@abc.com", phoneNumber = "1234567890"),
        )

        // TODO: Add assertions
    }
}
