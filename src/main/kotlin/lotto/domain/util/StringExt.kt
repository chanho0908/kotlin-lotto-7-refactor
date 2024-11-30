package lotto.domain.util

fun String.splitByComma() = this.split(",").filter { it.isNotBlank() }.map { it.trim() }