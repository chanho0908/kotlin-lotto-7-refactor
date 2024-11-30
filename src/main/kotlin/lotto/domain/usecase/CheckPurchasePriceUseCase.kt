package lotto.domain.usecase

import lotto.domain.rule.Error
import lotto.domain.rule.NumericConstants.ONE_THOUSAND_UNIT

class CheckPurchasePriceUseCase {
    operator fun invoke(input: String): Int{
        validate(input)
        return input.toInt()
    }

    private fun validate(input: String){
        isEmpty(input)
        isNumeric(input)
        isOneThousandUnit(input)
    }

    private fun isEmpty(input: String) {
        require(input.isNotBlank()) { Error.EMPTY }
    }

    private fun isNumeric(input: String) {
        requireNotNull(input.toIntOrNull()){ Error.ONLY_DIGIT }
    }

    private fun isOneThousandUnit(input: String){
        val value = input.toInt()
        require(
            value >= ONE_THOUSAND_UNIT.value &&
            value % ONE_THOUSAND_UNIT.value == 0
        ){
            Error.NOT_THOUSAND_UNIT
        }
    }
}