package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.util.logger
import org.springframework.stereotype.Component

@Component
class HelloTraceV2(

) {

    private val log = logger<HelloTraceV2>()

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message)
        return TraceStatus(
            traceId = traceId,
            startTimeMs = startTimeMs,
            message = message
        )
    }

    // V2 에서 추가
    fun beginSync(beforeTraceId: TraceId, message: String): TraceStatus {
        val nextId = beforeTraceId.createNextId()
        val startTimeMs = System.currentTimeMillis()
        log.info("[{}] {}{}", nextId.getId(), addSpace(START_PREFIX, nextId.getLevel()), message)
        return TraceStatus(
            traceId = nextId,
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
        val resultTimeMs = stopTimeMs - (status?.startTimeMs ?: 0)
        val traceId = status?.traceId
        if (e == null) {
            if (traceId != null) {
                log.info(
                    "[{}] {}{} time={}ms",
                    traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                    status.message,
                    resultTimeMs
                )
            }
        } else {
            if (traceId != null) {
                log.info(
                    "[{}] {}{} time={}ms ex={}",
                    traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()),
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