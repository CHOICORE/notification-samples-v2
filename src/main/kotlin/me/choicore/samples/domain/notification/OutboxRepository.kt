package me.choicore.samples.domain.notification

interface OutboxRepository {
    fun register(
        type: NotificationType,
        message: Message,
        sender: Sender,
        recipient: Recipient,
        errorMessage: String?,
    ): Long
}
