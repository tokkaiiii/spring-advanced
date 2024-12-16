package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.template.Callback
import hello.advanced.trace.strategy.code.template.TimeLogTemplate
import hello.advanced.util.logger
import org.junit.jupiter.api.Test

class TemplateCallbackTest {
    private val log = logger<TemplateCallbackTest>()

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    fun callbackV1() {
        TimeLogTemplate().also {
            it.execute(object : Callback {
                override fun call() {
                    log.info("비즈니스 로직1 실행")
                }
            })
        }
        TimeLogTemplate().also {
            it.execute(object : Callback {
                override fun call() {
                    log.info("비즈니스 로직2 실행")
                }
            })
        }
    }

}