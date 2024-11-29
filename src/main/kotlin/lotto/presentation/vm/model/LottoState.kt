package lotto.presentation.vm.model

import lotto.domain.rule.Output
import lotto.presentation.event.UiEvent

data class LottoState(
    val purchasePrice: Int,
    val purchaseLottoCount: Int,
    val uiEvent: UiEvent
) {
    companion object {
        fun create(): LottoState {
            return LottoState(
                0,
                0,
                UiEvent.OnUiEventInputPurchasePrice(Output.INPUT_PURCHASE_PRICE.msg)
            )
        }
    }
}