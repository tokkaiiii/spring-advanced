package hello.advanced.trace.callback

import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace

class TraceTemplate(
    private val trace: LogTrace
) {

    fun <T> execute(message: String, callback: TraceCallback<T>): T {
        var status: TraceStatus? = null
        try {
            status = trace.begin(message)
            // 로직 호출
            val result = callback.call() // T 타입 반환
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}