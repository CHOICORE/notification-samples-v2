package me.choicore.samples.infrastructure.jpa.notification.adapter

import me.choicore.samples.domain.notification.Message
import me.choicore.samples.domain.notification.NotificationType
import me.choicore.samples.domain.notification.OutboxRepository
import me.choicore.samples.domain.notification.Recipient
import me.choicore.samples.domain.notification.Sender
import me.choicore.samples.infrastructure.jpa.notification.OutboxEntity
import me.choicore.samples.infrastructure.jpa.notification.OutboxJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DefaultOutboxRepository(
    private val outboxJpaRepository: OutboxJpaRepository,
) : OutboxRepository {
    override fun register(
        type: NotificationType,
        message: Message,
        sender: Sender,
        recipient: Recipient,
        errorMessage: String?,
    ): Long =
        outboxJpaRepository
            .save(
                OutboxEntity(
                    type = type,
                    message = message,
                    sender = sender,
                    recipient = recipient,
                    errorMessage = errorMessage,
                    status = determineStatus(errorMessage),
                ),
            ).id

    private fun determineStatus(errorMessage: String?): OutboxEntity.Status =
        if (errorMessage != null) {
            OutboxEntity.Status.FAILED
        } else {
            OutboxEntity.Status.PENDING
        }
}
