package me.choicore.samples.infrastructure.jpa.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration(proxyBeanMethods = false)
@EnableJpaAuditing
@EntityScan(basePackages = ["me.choicore.samples.infrastructure.jpa"])
@EnableJpaRepositories(basePackages = ["me.choicore.samples.infrastructure.jpa"])
class JpaConfiguration
