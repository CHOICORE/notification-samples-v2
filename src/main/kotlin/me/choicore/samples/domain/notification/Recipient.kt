package me.choicore.samples.domain.notification

data class Recipient(
    val userId: Long? = null,
    val fullName: String,
    val emailAddress: String,
    val phoneNumber: String,
)
