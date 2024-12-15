package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.util.logger
import org.springframework.stereotype.Component

@Component
class HelloTraceV1(

) {

    private val log = logger<HelloTraceV1>()

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
        return TraceStatus(
            traceId = traceId,
            startTimeMs = startTimeMs,
            message = message
        )
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(status: TraceStatus?, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus?, e: Exception?) {
        val stopTimeMs = System.currentTimeMillis()
        val resultTimeMs = stopTimeMs - (status?.startTimeMs ?:0 )
        val traceId = status?.traceId
        if (e == null) {
            if (traceId != null) {
                log.info(
                    "[{}] {}{} time={}ms",
                    traceId.id,
                    addSpace(COMPLETE_PREFIX, traceId.level),
                    status.message,
                    resultTimeMs
                )
            }
        } else {
            if (traceId != null) {
                log.info(
                    "[{}] {}{} time={}ms ex={}",
                    traceId.id,
                    addSpace(EX_PREFIX, traceId.level),
                    status.message,
                    resultTimeMs,
                    e.toString()
                )
            }
        }
    }

    private companion object {
        fun addSpace(prefix: String, level: Int): String {
            val sb = StringBuilder()
            for (i in 0 until level) {
                if (i == level - 1) {
                    sb.append("|$prefix")
                } else {
                    sb.append("|  ")
                }
            }
            return sb.toString()
        }
    }


}

private const val START_PREFIX = "-->"
private const val COMPLETE_PREFIX = "<--"
private const val EX_PREFIX = "<X-"
