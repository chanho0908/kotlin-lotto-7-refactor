package lotto.domain.rule

enum class Error(private val msg: String) {
    EMPTY("빈 값이 입력 되었어요."),
    WINNING_DUPLICATED("당첨 번호는 중복될 수 없어요."),
    ONLY_DIGIT("0 이상의 정수만 입력해주세요."),
    NOT_THOUSAND_UNIT("1000원 단위로 입력해주세요"),
    INVALID_RANGE("로또 번호는 1~45 사이의 숫자로 입력해주세요."),
    INVALID_WINNING_SIZE("당첨 번호는 6개를 입력해주세요");

    override fun toString(): String = "$ERROR $msg"

    companion object {
        private const val ERROR = "[ERROR]"
    }

}