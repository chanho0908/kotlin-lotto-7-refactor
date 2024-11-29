package lotto.presentation.util

fun<T> retryWhenNoException(action : () -> T): T{
    while (true){
        try {
            return action()
        }catch (e: IllegalArgumentException){
            println(e)
        }
    }
}