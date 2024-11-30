package lotto.domain.usecase

import lotto.domain.rule.Error
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CheckPurchasePriceUseCaseTest {
    private lateinit var checkPurchasePriceUseCase: CheckPurchasePriceUseCase

    @BeforeEach
    fun setup(){
        checkPurchasePriceUseCase = CheckPurchasePriceUseCase()
    }

    @Test
    fun `입력값이 비어있을 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            checkPurchasePriceUseCase(" ")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.EMPTY}")
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다, abc, ###, 2147483648"])
    fun `구매 가격이 숫자가 아닐 때 테스트`(value: String){
        Assertions.assertThatThrownBy {
            checkPurchasePriceUseCase(value)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.ONLY_DIGIT}")
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "9999", "0000", "1001"])
    fun `천원 단위가 아닐 때 테스트`(value: String){
        Assertions.assertThatThrownBy {
            checkPurchasePriceUseCase(value)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.NOT_THOUSAND_UNIT}")
    }

    @Test
    fun `구매 금액 정상 입력 테스트`(){
        Assertions.assertThatCode { checkPurchasePriceUseCase("3000") }
            .doesNotThrowAnyException()
    }
}