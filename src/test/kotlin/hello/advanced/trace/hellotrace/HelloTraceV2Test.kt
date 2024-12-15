package hello.advanced.trace.hellotrace

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HelloTraceV2Test{

    @Test
    fun begin_end(){
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello1")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.end(status2)
        trace.end(status1)
    }

    @Test
    fun begin_exception(){
        val trace = HelloTraceV2()
        val status1 = trace.begin("hello1")
        val status2 = trace.beginSync(status1.traceId, "hello2")
        trace.exception(status2,IllegalStateException())
        trace.exception(status1,IllegalStateException())

    }


}