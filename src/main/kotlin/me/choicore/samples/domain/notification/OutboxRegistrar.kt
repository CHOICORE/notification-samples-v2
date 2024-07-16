package me.choicore.samples.domain.notification

import me.choicore.samples.domain.notification.template.PlaceholderRegistry
import me.choicore.samples.domain.notification.template.Template
import me.choicore.samples.domain.notification.template.TemplateRegistry
import org.springframework.stereotype.Service

@Service
class OutboxRegistrar(
    private val outboxRepository: OutboxRepository,
    private val templateRegistry: TemplateRegistry,
) {
    fun register(
        notificationType: NotificationType,
        dispatchStrategy: DispatchStrategy,
        templateName: String,
        placeholderRegistry: PlaceholderRegistry,
        sender: Sender,
        recipient: Recipient,
    ): Long {
        val template: Template = templateRegistry.getTemplate(templateName)

        var errorMessage: String? = null
        val renderedMessage: Message =
            try {
                template.render(placeholderRegistry)
            } catch (e: IllegalArgumentException) {
                errorMessage = e.message
                Message(
                    subject = template.subject,
                    content = template.content,
                )
            }

        val messageContext: MessageContext =
            MessageContext(
                notificationType = notificationType,
                dispatchStrategy = dispatchStrategy,
                sender = sender,
                message = renderedMessage,
                recipient = recipient,
            ).apply { errorMessage?.let { markAsImpossible(it) } }

        return outboxRepository.register(messageContext = messageContext)
    }

    fun register(
        notificationType: NotificationType,
        dispatchStrategy: DispatchStrategy,
        templateName: String,
        placeholderRegistry: PlaceholderRegistry,
        recipient: Recipient,
    ): Long =
        this.register(
            notificationType = notificationType,
            dispatchStrategy = dispatchStrategy,
            templateName = templateName,
            placeholderRegistry = placeholderRegistry,
            sender = Sender.ADMINISTRATOR,
            recipient = recipient,
        )
}
