package me.choicore.samples.domain.notification

interface OutboxRepository {
    fun register(messageContext: MessageContext): Long
}
