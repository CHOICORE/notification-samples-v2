package me.choicore.samples.infrastructure.jpa.notification.adapter

import me.choicore.samples.domain.notification.MessageContext
import me.choicore.samples.domain.notification.OutboxRepository
import me.choicore.samples.infrastructure.jpa.notification.OutboxEntity
import me.choicore.samples.infrastructure.jpa.notification.OutboxJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DefaultOutboxRepository(
    private val outboxJpaRepository: OutboxJpaRepository,
) : OutboxRepository {
    override fun register(messageContext: MessageContext): Long {
        val entity =
            OutboxEntity(
                type = messageContext.notificationType,
                dispatchStrategy = messageContext.dispatchStrategy,
                message = messageContext.message,
                sender = messageContext.sender,
                recipient = messageContext.recipient,
                errorMessage = messageContext.errorMessage,
                status = messageContext.status,
            )
        return outboxJpaRepository.save(entity).id
    }
}
