package me.choicore.samples.infrastructure.jpa.notification

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import me.choicore.samples.domain.notification.DispatchStatus
import me.choicore.samples.domain.notification.DispatchStrategy
import me.choicore.samples.domain.notification.Message
import me.choicore.samples.domain.notification.NotificationType
import me.choicore.samples.domain.notification.Recipient
import me.choicore.samples.domain.notification.Sender
import me.choicore.samples.infrastructure.jpa.PrimaryKey
import java.time.LocalDateTime

@Entity
@Table(name = "notification_outbox")
class OutboxEntity(
    @Enumerated(EnumType.STRING)
    val type: NotificationType,
    dispatchStrategy: DispatchStrategy,
    status: DispatchStatus,
    message: Message,
    sender: Sender,
    recipient: Recipient,
    errorMessage: String? = null,
) : PrimaryKey() {
    val senderId: Long = sender.userId
    val senderFullName: String = sender.name
    val senderEmailAddress: String = sender.emailAddress
    val senderContactNumber: String = sender.contactNumber
    val subject: String? = message.subject
    val content: String = message.content
    val recipientId: Long? = recipient.userId
    val recipientFullName: String = recipient.fullName
    val recipientEmailAddress: String = recipient.emailAddress
    val recipientPhoneNumber: String = recipient.phoneNumber

    @Enumerated(EnumType.STRING)
    val transferType: DispatchStrategy.Type = dispatchStrategy.type
    val transferScheduled: LocalDateTime = dispatchStrategy.scheduled

    var errorMessage: String? = errorMessage
        private set

    @Enumerated(EnumType.STRING)
    var status: DispatchStatus = status
        private set

    fun markAsSent() {
        status = DispatchStatus.SENT
    }

    fun markAsFailed() {
        status = DispatchStatus.FAILED
    }

    fun markAsSucceed() {
        status = DispatchStatus.SUCCEED
    }
}
