package lotto.domain.usecase

import lotto.domain.Lotto
import lotto.domain.rule.Rank
import lotto.domain.rule.Rank.Companion.getRank
import java.util.TreeSet

class OperateWinningUseCase {
    operator fun invoke(
        pickedLotto: List<TreeSet<Int>>,
        winningNumber: List<Int>,
        bonusNumber: Int,
        rankMap: Map<Rank, Int>
    ): Map<Rank, Int> {

        val lottoHandler = Lotto(winningNumber)
        val rank = rankMap.toMutableMap()
        pickedLotto.forEach {
            val matches = lottoHandler.getMatchCount(it)
            val rankResult = getRank(matches, it.contains(bonusNumber))
            rank[rankResult] = rank.getOrDefault(rankResult, 0) + 1
        }
        return rank
    }
}