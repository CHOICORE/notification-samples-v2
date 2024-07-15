package me.choicore.samples.domain.notification.template

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlaceholderTests {
    @Test
    fun t1() {
        Assertions
            .assertThatThrownBy {
                Placeholder("", "value")
            }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun t2() {
        Assertions
            .assertThatThrownBy {
                Placeholder("key", "")
            }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
