package lotto.presentation.vm

import lotto.presentation.vm.model.LottoState

class ViewModel {
    private val _state = LottoState.create()
    val state get() = _state

    fun onCompleteInputPurchasePrice(input: String){

    }
}