package lotto.domain.usecase

import lotto.domain.rule.Error
import lotto.domain.rule.NumericConstants.LOTTO_MAX
import lotto.domain.rule.NumericConstants.LOTTO_MIN

class CheckBonusNumberUseCase {
    operator fun invoke(winningNumber: List<Int>, input: String): Int {
        validate(input, winningNumber)
        return input.toInt()
    }

    private fun validate(input: String, winningNumber: List<Int>){
        isEmpty(input)
        isNumeric(input)
        isInvalidRange(input)
        isDuplicated(winningNumber, input)
    }

    private fun isEmpty(input: String){
        require(input.isNotBlank()) { Error.EMPTY }
    }

    private fun isNumeric(input: String) {
        requireNotNull(input.toIntOrNull()){ Error.ONLY_DIGIT }
    }

    private fun isInvalidRange(input: String) {
        val range = LOTTO_MIN.value..LOTTO_MAX.value
        require(input.toInt() in range ) { Error.INVALID_RANGE }
    }

    private fun isDuplicated(winningNumber: List<Int>, input: String) {
        require(winningNumber.contains(input.toInt()).not()) { Error.WINNING_DUPLICATED }
    }
}