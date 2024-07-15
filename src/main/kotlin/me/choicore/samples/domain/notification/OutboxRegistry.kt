package me.choicore.samples.domain.notification

import org.springframework.stereotype.Service

@Service
class OutboxRegistry(
    private val outboxRepository: OutboxRepository,
) {
    fun register(
        type: NotificationType,
        message: Message,
        sender: Sender,
        recipient: Recipient,
    ) {
        outboxRepository.register(type, message, sender, recipient)
    }
}
