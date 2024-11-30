package lotto.domain.usecase

import camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange
import lotto.domain.rule.NumericConstants.LOTTO_MIN
import lotto.domain.rule.NumericConstants.LOTTO_MAX
import lotto.domain.rule.NumericConstants.MAX_WINNING_SIZE
import java.util.TreeSet

class MakeLottoUseCase {
    operator fun invoke(cnt: Int): List<TreeSet<Int>> {
        val lotto = mutableListOf<TreeSet<Int>>()
        repeat(cnt) {
            lotto.add(
                TreeSet(pickUniqueNumbersInRange(LOTTO_MIN.value, LOTTO_MAX.value, MAX_WINNING_SIZE.value))
            )
        }
        return lotto
    }
}