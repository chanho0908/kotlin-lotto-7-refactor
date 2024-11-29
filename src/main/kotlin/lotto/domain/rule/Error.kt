package lotto.domain.rule

enum class Error(private val msg: String) {
    EMPTY("빈 값이 입력 되었어요."),
    DUPLICATED("당첨 번호는 중복될 수 없어요."),
    INVALID_INPUT("잘못된 입력입니다. 이름은 한/영/숫자로 2명 이상 입력해주세요."),
    ONLY_DIGIT("0 이상의 정수만 입력해주세요."),
    NOT_THOUSAND_UNIT("1000원 단위로 입력해주세요");

    override fun toString(): String = "$ERROR $msg"

    companion object {
        private const val ERROR = "[Error]"
    }

}