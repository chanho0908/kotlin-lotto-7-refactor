package lotto.domain.usecase

import lotto.domain.util.splitByComma
import lotto.domain.rule.Error
import lotto.domain.rule.NumericConstants
import lotto.domain.rule.NumericConstants.LOTTO_MAX
import lotto.domain.rule.NumericConstants.LOTTO_MIN

class CheckWinningNumberUseCase {
    operator fun invoke(input: String): List<Int> {
        defaultValidate(input)
        val spliterator = input.splitByComma()
        spliteratorValidate(spliterator)
        return spliterator.map { it.toInt() }
    }

    private fun defaultValidate(input: String) {
        isEmpty(input)
        isValidForm(input)
    }

    private fun isEmpty(input: String) {
        require(input.isNotBlank()) { Error.EMPTY }
    }

    private fun isValidForm(input: String) {
        val regex = Regex("^[0-9,]+")
        require(regex.matches(input)) { Error.INVALID_RANGE }
    }

    private fun spliteratorValidate(spliterator: List<String>) {
        isNumeric(spliterator)
        isInvalidSize(spliterator)
        isInvalidRange(spliterator)
        isDuplicated(spliterator)
    }

    private fun isNumeric(spliterator: List<String>) {
        spliterator.forEach {
            requireNotNull(it.toIntOrNull()) { Error.ONLY_DIGIT }
        }
    }

    private fun isInvalidSize(spliterator: List<String>){
        require(spliterator.size == NumericConstants.MAX_WINNING_SIZE.value){
            Error.INVALID_WINNING_SIZE
        }
    }

    private fun isInvalidRange(spliterator: List<String>) {
        val range = LOTTO_MIN.value..LOTTO_MAX.value
        require(spliterator.all { it.toInt() in range }) { Error.INVALID_RANGE }
    }

    private fun isDuplicated(spliterator: List<String>) {
        val map = spliterator.groupingBy { it }.eachCount()
        require(map.all { it.value == 1 }) { Error.WINNING_DUPLICATED }
    }
}