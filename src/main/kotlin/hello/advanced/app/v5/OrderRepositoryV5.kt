package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

@Repository
class OrderRepositoryV5(
    trace: LogTrace
) {

    private val template = TraceTemplate(trace)

    fun save(itemId: String) {
        template.execute("OrderRepository.save()",
            object : TraceCallback<Unit> {
                override fun call() {
                    if (itemId == "ex") {
                        throw IllegalStateException("예외 발생!")
                    }
                    sleep(1000)
                }
            })
    }
}