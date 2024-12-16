package hello.advanced.app.v5

import hello.advanced.trace.callback.TraceCallback
import hello.advanced.trace.callback.TraceTemplate
import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceV5(
    private val orderRepository: OrderRepositoryV5,
    private val trace: LogTrace
) {

    private val traceTemplate = TraceTemplate(trace)

    fun orderItem(itemId: String) {
        traceTemplate.execute(message = "OrderService.orderItem()",
            object : TraceCallback<Unit> {
                override fun call() {
                    orderRepository.save(itemId)
                }
            })
    }
}
