package lotto.presentation.controller

import lotto.presentation.InputView
import lotto.presentation.OutputView
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
            is UiEvent.OnUiEventBonusNumber -> onUiEventBonusNumber(event.msg)
            is UiEvent.OnUiEventDisplayResult -> onUiEventDisplayResult(event.msg)
            is UiEvent.OnUiEventInputWinningNumber -> onUiEventInputWinningNumber(event.msg)
        }
    }

    private fun onUiEventInputPurchasePrice(msg: String) {
        retryWhenNoException {
            outputView.printMessage(msg)
            val input = inputView.readItem()
            //viewModel.
        }
        checkUiEvent()
    }

    private fun onUiEventBonusNumber(msg: String) {
        retryWhenNoException {
            outputView.printMessage(msg)
            val input = inputView.readItem()
            //viewModel.
        }
        checkUiEvent()
    }

    private fun onUiEventDisplayResult(msg: String) {
        retryWhenNoException {
            outputView.printMessage(msg)
            val input = inputView.readItem()
            //viewModel.
        }
        checkUiEvent()
    }

    private fun onUiEventInputWinningNumber(msg: String) {
        retryWhenNoException {
            outputView.printMessage(msg)
            val input = inputView.readItem()
            //viewModel.
        }
        checkUiEvent()
    }
}