package lotto.domain.util

import java.text.NumberFormat
import java.util.Locale

fun Int.toKoreanUnit(): String = NumberFormat.getNumberInstance(Locale.KOREAN).format(this)

fun Double.convertRoundAtTwoDecimal(): String = "%.1f".format(this)