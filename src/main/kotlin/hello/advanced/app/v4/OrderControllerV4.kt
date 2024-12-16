package hello.advanced.app.v4

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV4(
    private val orderService: OrderServiceV4,
    private val trace: LogTrace
) {

    @GetMapping("/v4/request")
    fun request(itemId: String): String {
        // AbstractTemplate 을 String 타입으로 설정
        val template = object : AbstractTemplate<String>(trace) {
            override fun call(): String {
                orderService.orderItem(itemId)  // 실제 비즈니스 로직 호출
                return "ok"  // 결과를 반환
            }
        }
        return template.execute(message = "OrderController.request()")  // 실행 결과 반환
    }
}
