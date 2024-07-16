package me.choicore.samples.domain.notification

class MessageContext(
    val notificationType: NotificationType,
    val dispatchStrategy: DispatchStrategy = DispatchStrategy.Immediate(),
    val sender: Sender,
    val message: Message,
    val recipient: Recipient,
) {
    var errorMessage: String? = null
        private set

    var status: DispatchStatus = DispatchStatus.PENDING
        private set

    fun markAsImpossible(errorMessage: String) {
        this.errorMessage = errorMessage
        this.status = DispatchStatus.IMPOSSIBLE
    }
}
