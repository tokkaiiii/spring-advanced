package hello.advanced.trace.template

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

abstract class AbstractTemplate<T>(
    private val trace: LogTrace
) {

    fun execute(message: String): T {
        var status: TraceStatus? = null
        try {
            status = trace.begin(message)
            // 로직 호출
            val result = call() // T 타입 반환
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }

    abstract fun call(): T // 반환 타입을 T로 지정

}