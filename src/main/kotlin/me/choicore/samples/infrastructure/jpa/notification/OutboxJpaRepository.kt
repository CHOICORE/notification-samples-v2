package me.choicore.samples.infrastructure.jpa.notification

import org.springframework.data.jpa.repository.JpaRepository

interface OutboxJpaRepository : JpaRepository<OutboxEntity, Long>
