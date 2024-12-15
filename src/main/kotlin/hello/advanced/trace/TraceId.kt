package hello.advanced.trace

import java.util.*

data class TraceId(
    val id: String = UUID.randomUUID().toString().substring(0,8),
    var level: Int = 0
){

    fun createNextId() = TraceId(id,level+1)

    fun createPreviousId() = TraceId(id,level-1)

    fun isFirstLevel() = level == 0
}
