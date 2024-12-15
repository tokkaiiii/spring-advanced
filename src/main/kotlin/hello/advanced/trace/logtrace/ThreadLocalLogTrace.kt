package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.util.logger

class ThreadLocalLogTrace : LogTrace {

    private var traceIdHolder = ThreadLocal<TraceId>()
    private val log = logger<ThreadLocalLogTrace>()

    private fun syncTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId == null) {
            traceIdHolder.set(TraceId())
        } else {
            traceIdHolder.set(traceId.createNextId())
        }
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        log.info(
            "[{}] {}{}",
            traceId.id,
            addSpace(START_PREFIX, traceId.level),
            message
        )
        return TraceStatus(
            traceId = traceId,
            startTimeMs = startTimeMs,
            message = message
        )
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus?, e: Exception) {
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
        releaseTraceId()
    }

    private fun releaseTraceId() {
        val traceId = traceIdHolder.get()
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove()
        } else {
            traceIdHolder.set(traceId.createPreviousId())
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