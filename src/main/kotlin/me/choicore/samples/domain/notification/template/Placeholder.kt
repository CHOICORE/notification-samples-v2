package me.choicore.samples.domain.notification.template

data class Placeholder(
    val target: String,
    val replacement: String,
) {
    init {
        require(target.isNotBlank()) { "target must not be blank" }
        require(replacement.isNotBlank()) { "replacement must not be blank" }
    }
}
