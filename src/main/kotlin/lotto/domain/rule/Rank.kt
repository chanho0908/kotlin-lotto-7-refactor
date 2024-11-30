package lotto.domain.rule

import lotto.domain.rule.Output.Companion.bonusMatchFormat
import lotto.domain.rule.Output.Companion.matchFormat
import lotto.domain.util.toKoreanUnit

enum class Rank(val reward: Int, val matchCount: Int) {
    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    NONE(0, 0);

    companion object {

        fun getRank(matchCount: Int, bonusMatched: Boolean): Rank {
            return when (matchCount) {
                6 -> FIRST
                5 -> if (bonusMatched) SECOND else THIRD
                4 -> FOURTH
                3 -> FIFTH
                else -> NONE
            }
        }

        fun getRankMessage(rank: Rank, winning: Int): String {
            return when (rank) {
                SECOND -> bonusMatchFormat(rank.matchCount, rank.reward.toKoreanUnit(), winning)
                else -> matchFormat(rank.matchCount, rank.reward.toKoreanUnit(), winning)
            }
        }
    }
}