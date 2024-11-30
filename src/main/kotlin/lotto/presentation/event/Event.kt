package lotto.presentation.event

sealed interface UiEvent {
    val msg: String
    data class OnUiEventInputPurchasePrice(override val msg: String): UiEvent
    data class OnUiEventInputWinningNumber(override val msg: String): UiEvent
    data class OnUiEventBonusNumber(override val msg: String): UiEvent
    data class OnUiEventDisplayResult(override val msg: String): UiEvent
}