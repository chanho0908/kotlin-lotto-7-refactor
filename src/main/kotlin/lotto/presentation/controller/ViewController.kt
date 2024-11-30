package lotto.presentation.controller

import lotto.presentation.view.InputView
import lotto.presentation.view.OutputView
import lotto.presentation.event.UiEvent
import lotto.presentation.util.retryWhenNoException
import lotto.presentation.vm.ViewModel

class ViewController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val viewModel: ViewModel
) {
    init {
        checkUiEvent()
    }

    private fun checkUiEvent() {
        when(val event = viewModel.state.uiEvent){
            is UiEvent.OnUiEventInputPurchasePrice -> onUiEventInputPurchasePrice(event.msg)
            is UiEvent.OnUiEventInputWinningNumber -> onUiEventInputWinningNumber(event.msg)
            is UiEvent.OnUiEventBonusNumber -> onUiEventBonusNumber(event.msg)
            is UiEvent.OnUiEventDisplayResult -> onUiEventDisplayResult(event.msg)
        }
    }

    private fun onUiEventInputPurchasePrice(msg: String) {
        retryWhenNoException {
            outputView.printMessage(msg)
            val input = inputView.readItem()
            viewModel.onCompleteInputPurchasePrice(input)
        }
        checkUiEvent()
    }

    private fun onUiEventInputWinningNumber(msg: String) {
        retryWhenNoException {
            outputView.printMessage(msg)
            val input = inputView.readItem()
            viewModel.onCompleteInputWinningNumber(input)
        }
        checkUiEvent()
    }

    private fun onUiEventBonusNumber(msg: String) {
        retryWhenNoException {
            outputView.printMessage(msg)
            val input = inputView.readItem()
            viewModel.onCompleteBonusNumber(input)
        }
        checkUiEvent()
    }

    private fun onUiEventDisplayResult(msg: String) {
        outputView.printMessage(msg)
    }
}