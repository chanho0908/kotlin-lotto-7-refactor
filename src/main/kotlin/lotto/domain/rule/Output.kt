package lotto.domain.rule

enum class Output(val msg: String) {
    INPUT_PURCHASE_PRICE("구입금액을 입력해 주세요."),
    OUTPUT_PURCHASE_LOTTO_COUNT("%s개를 구매했습니다."),
    INPUT_WINNING_NUMBER("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    WINNING_RESULT("당첨 통계\n---"),
    MATCH("%s개 일치 (%s원) - %s개"),
    BONUS_MATCH("%s개 일치, 보너스 볼 일치 (%s원) - %s개"),
    RATE_OF_RETURN("총 수익률은 %s입니다.");

    override fun toString(): String = msg

    companion object {
        fun purchaseLottoCountFormat(count: String): String{
            return OUTPUT_PURCHASE_LOTTO_COUNT.toString().format(count)
        }

        fun matchFormat(prizeCount: String, prizePrice: String, matchCount: String): String {
            return MATCH.toString().format(prizeCount, prizePrice, matchCount)
        }

        fun bonusMatchFormat(prizeCount: String, prizePrice: String, matchCount: String): String {
            return BONUS_MATCH.toString().format(prizeCount, prizePrice, matchCount)
        }

        fun rateOfReturnFormat(rateOfReturn: String): String{
            return RATE_OF_RETURN.toString().format(rateOfReturn)
        }
    }
}