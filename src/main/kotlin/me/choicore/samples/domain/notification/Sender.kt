package me.choicore.samples.domain.notification

data class Sender(
    val userId: Long,
    val fullName: String,
    val emailAddress: String,
    val contactNumber: String,
)
