package lotto.domain.rule

import lotto.domain.rule.Output.Companion.bonusMatchFormat
import lotto.domain.rule.Output.Companion.matchFormat
import lotto.domain.rule.Output.Companion.purchaseLottoCountFormat
import lotto.domain.rule.Output.Companion.rateOfReturnFormat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OutputTest {

    @Test
    fun `구매한 로또 개수 출력 테스트`(){
        val expected = purchaseLottoCountFormat("3")
        Assertions.assertEquals(expected, "3개를 구매했습니다.")
    }

    @Test
    fun `보너스 번호를 제외한 당첨 통계 출력 테스트`(){
        val expected = matchFormat("3", "5,000", "1")
        Assertions.assertEquals(expected, "3개 일치 (5,000원) - 1개")
    }

    @Test
    fun `보너스 번호를 포함한 당첨 통계 출력 테스트`(){
        val expected = bonusMatchFormat("5", "30,000,000", "0")
        Assertions.assertEquals(expected, "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개")
    }

    @Test
    fun `총 수익률 출력 테스트`(){
        val expected = rateOfReturnFormat("62.5%")
        Assertions.assertEquals(expected, "총 수익률은 62.5%입니다.")
    }
}

//fun matchFormat(prizeCount: String, prizePrice: String, matchCount: String): String {
//            return MATCH.toString().format(prizeCount, prizePrice, matchCount)
//        }
//
//        fun bonusMatchFormat(prizeCount: String, prizePrice: String, matchCount: String): String {
//            return BONUS_MATCH.toString().format(prizeCount, prizePrice, matchCount)
//        }
//
//        fun rateOfReturnFormat(rateOfReturn: String): String{
//            return RATE_OF_RETURN.toString().format(rateOfReturn)
//        }