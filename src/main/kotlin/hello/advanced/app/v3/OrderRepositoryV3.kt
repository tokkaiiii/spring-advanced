package hello.advanced.app.v3

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

@Repository
class OrderRepositoryV3(
    private val trace: LogTrace
) {
    fun save(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null
        try {
            status = trace.begin(" OrderRepository.save()")
            // 저장 로직
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생!")
            }
            sleep(1000)
            trace.end(status)
        } catch (e: Exception) {
            trace.exception(status, e)
            throw e
        }
    }
}