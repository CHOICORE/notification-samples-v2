package me.choicore.samples.domain.notification

import me.choicore.samples.domain.notification.template.PlaceholderRegistry
import me.choicore.samples.domain.notification.template.Template
import me.choicore.samples.domain.notification.template.TemplateRegistry
import org.springframework.stereotype.Service

@Service
class NotificationManager(
    private val outboxRegistry: OutboxRegistry,
    private val templateRegistry: TemplateRegistry,
) {
    fun append(
        type: NotificationType,
        templateName: String,
        placeholderRegistry: PlaceholderRegistry,
        sender: Sender,
        recipient: Recipient,
    ): Long {
        val template: Template = templateRegistry.getTemplate(templateName)
        val result: Pair<Message, String?> = generateMessageAndCaptureError(template, placeholderRegistry)
        return outboxRegistry.register(
            type = type,
            message = result.first,
            sender = sender,
            recipient = recipient,
            errorMessage = result.second,
        )
    }

    fun append(
        type: NotificationType,
        templateName: String,
        placeholderRegistry: PlaceholderRegistry,
        recipient: Recipient,
    ): Long = this.append(type, templateName, placeholderRegistry, Sender.ADMINISTRATOR, recipient)

    private fun generateMessageAndCaptureError(
        template: Template,
        placeholderRegistry: PlaceholderRegistry,
    ): Pair<Message, String?> =
        try {
            Pair(template.render(placeholderRegistry), null)
        } catch (e: IllegalArgumentException) {
            Pair(
                Message(
                    subject = template.subject,
                    content = template.content,
                ),
                e.message,
            )
        }
}
