package me.choicore.samples.domain.notification.template

class PlaceholderRegistry {
    private val placeholders = mutableMapOf<String, Placeholder>()

    fun getPlaceholders(): List<Placeholder> = this.placeholders.values.toList()

    fun getPlaceholder(target: String): Placeholder = this.placeholders[target] ?: throw IllegalStateException("Placeholder not exists")

    fun addPlaceholder(placeholder: Placeholder) {
        this.placeholders[placeholder.target] = placeholder
    }

    fun addPlaceholder(
        target: String,
        replacement: Any,
    ) {
        addPlaceholder(Placeholder(target, replacement.toString()))
    }

    fun addPlaceholders(placeholder: Map<String, Any>) {
        placeholder.forEach { (target: String, replacement: Any) ->
            addPlaceholder(target, replacement)
        }
    }

    fun addPlaceholders(placeholders: List<Placeholder>) {
        placeholders.forEach { addPlaceholder(it) }
    }

    fun addPlaceholders(vararg placeholders: Placeholder) {
        addPlaceholders(placeholders.toList())
    }

    fun addPlaceholders(vararg placeholders: Pair<String, Any>) {
        addPlaceholders(placeholders.map { Placeholder(it.first, it.second.toString()) })
    }
}
