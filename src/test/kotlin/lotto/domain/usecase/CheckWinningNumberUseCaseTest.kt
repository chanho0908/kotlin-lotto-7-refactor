package lotto.domain.usecase

import lotto.domain.rule.Error
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CheckWinningNumberUseCaseTest {
    private lateinit var checkWinningNumberUseCase: CheckWinningNumberUseCase

    @BeforeEach
    fun setUp(){
        checkWinningNumberUseCase = CheckWinningNumberUseCase()
    }

    @Test
    fun `입력값이 비어있을 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            checkWinningNumberUseCase("   ")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.EMPTY}")
    }

    @Test
    fun `입력 형식이 잘못되었을 때 예외 테스트`(){
        Assertions.assertThatThrownBy {
            checkWinningNumberUseCase("1;2,3,4,5,6")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.INVALID_RANGE}")
    }

    @Test
    fun `로또 번호를 6개를 초과했을 때 테스트`(){
        Assertions.assertThatThrownBy {
            checkWinningNumberUseCase("1,2,3,4,4,4,4,4,4,4")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.INVALID_WINNING_SIZE}")
    }


    @Test
    fun `로또 번호가 범위를 초과했을 때 테스트`(){
        Assertions.assertThatThrownBy {
            checkWinningNumberUseCase("1,2,3,64,4,4")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.INVALID_RANGE}")
    }

    @Test
    fun `로또 번호가 중복되었을 때 테스트`(){
        Assertions.assertThatThrownBy {
            checkWinningNumberUseCase("1,2,3,4,4,4")
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("${Error.WINNING_DUPLICATED}")
    }
}