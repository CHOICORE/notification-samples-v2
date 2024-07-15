package me.choicore.samples.infrastructure.jpa.notification

import jakarta.persistence.Entity
import jakarta.persistence.Table
import me.choicore.samples.domain.notification.Message
import me.choicore.samples.domain.notification.NotificationType
import me.choicore.samples.domain.notification.Recipient
import me.choicore.samples.domain.notification.Sender
import me.choicore.samples.infrastructure.jpa.PrimaryKey

@Entity
@Table(name = "notification_outbox")
class OutboxEntity(
    val type: NotificationType,
    status: Status = Status.PENDING,
    message: Message,
    sender: Sender,
    recipient: Recipient,
) : PrimaryKey() {
    val senderId: Long = sender.userId
    val senderFullName: String = sender.fullName
    val senderEmailAddress: String = sender.emailAddress
    val senderContactNumber: String = sender.contactNumber
    val subject: String? = message.subject
    val content: String = message.content
    val recipientId: Long? = recipient.userId
    val recipientFullName: String = recipient.fullName
    val recipientEmailAddress: String = recipient.emailAddress
    val recipientPhoneNumber: String = recipient.phoneNumber
    var status: Status = status
        private set

    fun markAsSent() {
        status = Status.SENT
    }

    fun markAsFailed() {
        status = Status.FAILED
    }

    fun markAsSucceed() {
        status = Status.SUCCEED
    }

    enum class Status {
        PENDING,
        SENT,
        FAILED,
        SUCCEED,
    }
}
