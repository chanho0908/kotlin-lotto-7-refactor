package lotto.app

import lotto.domain.usecase.CheckBonusNumberUseCase
import lotto.domain.usecase.CheckPurchasePriceUseCase
import lotto.domain.usecase.CheckWinningNumberUseCase
import lotto.domain.usecase.MakeLottoUseCase
import lotto.domain.usecase.OperateWinningUseCase
import lotto.presentation.view.InputView
import lotto.presentation.view.OutputView
import lotto.presentation.controller.ViewController
import lotto.presentation.vm.ViewModel

class DependencyInjector {

    fun injectViewController(): ViewController {
        val inputView = injectInputView()
        val outputView = injectOutPutView()
        val viewModel = injectViewModel()
        return ViewController(inputView, outputView, viewModel)
    }

    private fun injectViewModel(): ViewModel {
        val checkPurchasePriceUseCase = CheckPurchasePriceUseCase()
        val checkWinningNumberUseCase = CheckWinningNumberUseCase()
        val checkBonusNumberUseCase = CheckBonusNumberUseCase()
        val makeLottoUseCase = MakeLottoUseCase()
        val operateWinningUseCase = OperateWinningUseCase()
        return ViewModel(
            checkPurchasePriceUseCase,
            checkWinningNumberUseCase,
            checkBonusNumberUseCase,
            makeLottoUseCase,
            operateWinningUseCase
        )
    }

    private fun injectInputView() = InputView()
    private fun injectOutPutView() = OutputView()
}