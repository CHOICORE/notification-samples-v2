package me.choicore.samples.domain.notification.template

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class TemplateRegistryTests(
    private val templateRegistry: TemplateRegistry,
) {
    @Test
    fun t1() {
        val definition =
            TemplateDefinition(
                type = TemplateType.SUBJECT_AND_CONTENT,
                name = "welcome-email-template",
                description = "Welcome template",
                subject = "Welcome, {{name}}!",
                content = "Hello, {{name}}!, How are you? {{name}}!",
                startWith = "{{",
                endWith = "}}",
            )

        templateRegistry.register(definition)
    }

    @Test
    fun t2() {
        val definition =
            TemplateDefinition(
                type = TemplateType.CONTENT,
                name = "welcome-push-template",
                description = "Welcome template",
                content = "Hello, {{name}}!, How are you? {{name}}!",
                startWith = "{{",
                endWith = "}}",
            )

        templateRegistry.register(definition)
    }
}
