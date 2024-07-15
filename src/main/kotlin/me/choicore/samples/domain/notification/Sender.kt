package me.choicore.samples.domain.notification

data class Sender(
    val userId: Long,
    val name: String,
    val emailAddress: String,
    val contactNumber: String,
) {
    companion object {
        val ADMINISTRATOR =
            Sender(
                userId = 1L,
                name = "Administrator",
                emailAddress = "core@abc.com",
                contactNumber = "1234567890",
            )
    }
}
