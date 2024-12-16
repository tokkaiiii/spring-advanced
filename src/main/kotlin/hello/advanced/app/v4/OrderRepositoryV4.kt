package hello.advanced.app.v4

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

@Repository
class OrderRepositoryV4(
    private val trace: LogTrace
) {
    fun save(itemId: String) {
        val template = object : AbstractTemplate<Unit>(trace) {
            override fun call() {
                if (itemId == "ex"){
                    throw IllegalStateException("예외 발생!")
                }
                sleep(1000)
            }
        }
        template.execute(message = "OrderRepository.save()")
    }
}