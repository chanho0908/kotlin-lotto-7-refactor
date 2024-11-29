package lotto.domain.usecase

import lotto.domain.rule.Error
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CheckBonusNumberUseCaseTest {
    private lateinit var checkBonusNumberUseCase: CheckBonusNumberUseCase

    @BeforeEach
    fun setUp(){
        checkBonusNumberUseCase = CheckBonusNumberUseCase()
    }

    @Test
    fun `입력값이 비어있을 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            checkBonusNumberUseCase(emptyList(),"   ")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.EMPTY}")
    }

    @Test
    fun `시도 횟수 입력값이 음수일 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            checkBonusNumberUseCase(emptyList(), "-1")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.INVALID_RANGE}")
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다, abc, ###, 2147483648"])
    fun `시도 횟수 입력값이 숫자가 아닐 때 테스트`(value: String){
        Assertions.assertThatThrownBy {
            checkBonusNumberUseCase(emptyList(), value)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.ONLY_DIGIT}")
    }

    @Test
    fun `로또 번호가 범위를 초과했을 때 테스트`(){
        Assertions.assertThatThrownBy {
            checkBonusNumberUseCase(emptyList(),"55")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.INVALID_RANGE}")
    }

    @Test
    fun `로또 번호가 중복되었을 때 테스트`(){
        Assertions.assertThatThrownBy {
            checkBonusNumberUseCase(listOf(1,2,3,4,5,6),"1")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.WINNING_DUPLICATED}")
    }
}