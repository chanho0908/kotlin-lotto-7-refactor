package lotto.domain.rule

enum class Output(val msg: String) {
    INPUT_PURCHASE_PRICE("구입금액을 입력해 주세요."),
    OUTPUT_PURCHASE_LOTTO_COUNT("\n%d개를 구매했습니다.\n"),
    INPUT_WINNING_NUMBER("\n\n당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER("\n보너스 번호를 입력해 주세요."),
    WINNING_RESULT("\n당첨 통계\n---"),
    MATCH("%d개 일치 (%s원) - %d개\n"),
    BONUS_MATCH("%s개 일치, 보너스 볼 일치 (%s원) - %s개\n"),
    RATE_OF_RETURN("총 수익률은 %s입니다.");

    override fun toString(): String = msg

    companion object {
        fun purchaseLottoCountFormat(count: Int): String{
            return OUTPUT_PURCHASE_LOTTO_COUNT.toString().format(count)
        }

        fun matchFormat(prizeCount: Int, prizePrice: String, matchCount: Int): String {
            return MATCH.toString().format(prizeCount, prizePrice, matchCount)
        }

        fun bonusMatchFormat(prizeCount: Int, prizePrice: String, matchCount: Int): String {
            return BONUS_MATCH.toString().format(prizeCount, prizePrice, matchCount)
        }

        fun rateOfReturnFormat(rateOfReturn: String): String{
            return RATE_OF_RETURN.toString().format(rateOfReturn)
        }
    }
}