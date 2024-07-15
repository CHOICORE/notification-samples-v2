package me.choicore.samples.domain.notification.template

import me.choicore.samples.domain.notification.Message

class Template(
    val id: Long,
    val type: TemplateType,
    val name: String,
    val description: String,
    val subject: String? = null,
    val content: String,
    val startWith: String = DEFAULT_PLACEHOLDER_START_WITH,
    val endWith: String = DEFAULT_PLACEHOLDER_END_WITH,
) {
    private val placeholderRegex: Regex = Regex(Regex.escape(startWith) + ".*?" + Regex.escape(endWith))

    companion object {
        const val DEFAULT_PLACEHOLDER_START_WITH = "{{"
        const val DEFAULT_PLACEHOLDER_END_WITH = "}}"
    }

    fun render(registry: PlaceholderRegistry): Message =
        Message(
            subject = subject?.let { replacement(subject, registry) },
            content = replacement(content, registry),
        ).also { checkForResolved(it) }

    private fun replacement(
        target: String,
        registry: PlaceholderRegistry,
    ) = registry.getPlaceholders().fold(target) { acc, placeholder ->
        acc.replace(startWith + placeholder.target.trim() + endWith, placeholder.replacement)
    }

    private fun checkForResolved(message: Message) {
        val text: String = message.content + message.subject.orEmpty()
        require(!placeholderRegex.containsMatchIn(text)) {
            "Unresolved placeholders: ${
                placeholderRegex
                    .findAll(text)
                    .distinctBy { it.value }
                    .joinToString(", ") { it.value }
            }"
        }
    }

    fun hasPlaceholders(): Boolean = placeholderRegex.containsMatchIn(this.content + this.subject.orEmpty())

    fun extractPlaceholders(): Set<String> = doExtract(content + subject.orEmpty())

    private fun doExtract(text: String): Set<String> {
        val placeholders = mutableSetOf<String>()
        var startIndex = 0
        while (true) {
            val start = text.indexOf(startWith, startIndex)
            if (start == -1) {
                break
            }
            val end = text.indexOf(endWith, start + startWith.length)
            if (end == -1) {
                break
            }
            placeholders.add(startWith + text.substring(start + startWith.length, end) + endWith)
            startIndex = end + endWith.length
        }
        return placeholders
    }
}
