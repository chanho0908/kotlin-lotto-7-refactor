package lotto.presentation.vm.model

import lotto.domain.rule.Output
import lotto.domain.rule.Output.Companion.purchaseLottoCountFormat
import lotto.domain.rule.Output.Companion.rateOfReturnFormat
import lotto.domain.rule.Rank
import lotto.domain.rule.Rank.Companion.getRankMessage
import lotto.domain.util.convertRoundAtTwoDecimal
import lotto.presentation.event.UiEvent
import java.util.TreeSet

data class LottoState(
    val purchasePrice: Int,
    val purchaseLottoCount: Int,
    val pickedLotto: List<TreeSet<Int>>,
    val winningNumber: List<Int>,
    val bonusNumber: Int,
    val winningResult: Map<Rank, Int>,
    val uiEvent: UiEvent
) {
    val purchaseLottoCountMessage
        get() = purchaseLottoCountFormat(purchaseLottoCount)

    val pickedLottoMessage
        get () = pickedLotto.joinToString("\n")

    fun makeWinningResultMessage(): String{
        val sb = StringBuilder()
        winningResult.forEach { (rank, v) ->
            if (rank != Rank.NONE) sb.append(getRankMessage(rank, v))
        }
        return sb.toString()
    }

    fun makeRateOfReturn(): String {
        var sum = 0
        winningResult.forEach { (rank, v) ->
            sum += rank.reward * v
        }

        val rateOfReturn = "${(sum / purchasePrice.toDouble() * 100).convertRoundAtTwoDecimal()}%"
        return rateOfReturnFormat(rateOfReturn)
    }

    companion object {
        fun create(): LottoState {
            return LottoState(
                0,
                0,
                emptyList(),
                emptyList(),
                0,
                emptyMap(),
                UiEvent.OnUiEventInputPurchasePrice(Output.INPUT_PURCHASE_PRICE.msg)
            )
        }
    }
}