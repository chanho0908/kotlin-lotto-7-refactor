package lotto.domain

import lotto.domain.rule.Error
import java.util.TreeSet

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "[ERROR] 로또 번호는 6개여야 합니다." }
        require(numbers.distinct().size == numbers.size){ Error.WINNING_DUPLICATED }
    }

    fun getMatchCount(pickedLotto: TreeSet<Int>): Int {
        return numbers.count { pickedLotto.contains(it) }
    }
}
