package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.util.logger

class FieldLogTrace(
) : LogTrace {

    private var traceIdHolder: TraceId? = null // traceId 동기화, 동시성 이슈 발생
    private val log = logger<FieldLogTrace>()

    private fun syncTraceId() {
        traceIdHolder = if (traceIdHolder == null) {
            TraceId()
        } else {
            traceIdHolder!!.createNextId()
        }
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = this.traceIdHolder!!
        val startTimeMs = System.currentTimeMillis()
        log.info(
            "[{}] {}{}",
            traceId.getId(),
            addSpace(START_PREFIX, traceId.getLevel()),
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
        releaseTraceId()
    }

    private fun releaseTraceId() {
        if (traceIdHolder!!.isFirstLevel()) {
            traceIdHolder = null
        } else {
            traceIdHolder!!.createPreviousId()
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