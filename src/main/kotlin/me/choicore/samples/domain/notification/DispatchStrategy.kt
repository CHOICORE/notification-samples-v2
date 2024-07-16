package me.choicore.samples.domain.notification

import java.time.LocalDateTime

sealed interface DispatchStrategy {
    val type: Type
    val scheduled: LocalDateTime

    class Immediate : DispatchStrategy {
        override val scheduled: LocalDateTime = LocalDateTime.now()
        override val type = Type.IMMEDIATE
    }

    data class Scheduled(
        override val scheduled: LocalDateTime,
    ) : DispatchStrategy {
        override val type = Type.SCHEDULED
    }

    enum class Type {
        IMMEDIATE,
        SCHEDULED,
    }
}
