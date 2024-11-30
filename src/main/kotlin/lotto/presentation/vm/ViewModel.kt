package lotto.presentation.vm

import lotto.domain.rule.NumericConstants.ONE_THOUSAND_UNIT
import lotto.domain.rule.Output
import lotto.domain.usecase.CheckBonusNumberUseCase
import lotto.domain.usecase.CheckPurchasePriceUseCase
import lotto.domain.usecase.CheckWinningNumberUseCase
import lotto.domain.usecase.MakeLottoUseCase
import lotto.domain.usecase.OperateWinningUseCase
import lotto.presentation.event.UiEvent
import lotto.presentation.vm.model.LottoState

class ViewModel(
    private val checkPurchasePriceUseCase: CheckPurchasePriceUseCase,
    private val checkWinningNumberUseCase: CheckWinningNumberUseCase,
    private val checkBonusNumberUseCase: CheckBonusNumberUseCase,
    private val makeLottoUseCase: MakeLottoUseCase,
    private val operateWinningUseCase: OperateWinningUseCase
) {
    private var _state = LottoState.create()
    val state get() = _state

    fun onCompleteInputPurchasePrice(input: String) {
        val purchasedPrice = checkPurchasePriceUseCase(input)
        val purchasedCount = input.toInt() / ONE_THOUSAND_UNIT.value
        _state = _state.copy(purchasePrice = purchasedPrice, purchaseLottoCount = purchasedCount)
        pickLotto()
        changeUiEventToInputWinningNumber()
    }

    private fun changeUiEventToInputWinningNumber() {
        _state = _state.copy(
            uiEvent = UiEvent.OnUiEventInputWinningNumber(
                _state.purchaseLottoCountMessage +
                        _state.pickedLottoMessage +
                        Output.INPUT_WINNING_NUMBER.msg
            )
        )
    }

    private fun pickLotto() {
        _state = _state.copy(pickedLotto = makeLottoUseCase(_state.purchaseLottoCount))
    }

    fun onCompleteInputWinningNumber(input: String) {
        _state = _state.copy(
            winningNumber = checkWinningNumberUseCase(input),
            uiEvent = UiEvent.OnUiEventBonusNumber(Output.INPUT_BONUS_NUMBER.msg)
        )
    }

    fun onCompleteBonusNumber(input: String) {
        _state = _state.copy(bonusNumber = checkBonusNumberUseCase(_state.winningNumber, input))
        _state = _state.copy(
            winningResult = operateWinningUseCase(
                _state.pickedLotto,
                _state.winningNumber,
                _state.bonusNumber,
                _state.winningResult
            )
        )
        makeResult()
    }

    private fun makeResult() {
        _state = _state.copy(
            uiEvent = UiEvent.OnUiEventDisplayResult(
                Output.WINNING_RESULT.msg +
                        _state.makeWinningResultMessage() +
                        _state.makeRateOfReturn()
            )
        )
    }
}