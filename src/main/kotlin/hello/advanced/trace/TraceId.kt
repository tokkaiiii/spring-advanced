package hello.advanced.trace

import java.util.*

data class TraceId(
    private val id: String = UUID.randomUUID().toString().substring(0,8),
    private val level: Int = 0
){

    fun createNextId() = TraceId(id,level+1)

    fun createPreviousId() = TraceId(id,level-1)

    fun isFirstLevel() = level == 0

    fun getId() = id

    fun getLevel() = level
}
