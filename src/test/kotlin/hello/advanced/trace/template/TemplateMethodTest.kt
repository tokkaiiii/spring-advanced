package hello.advanced.trace.template

import hello.advanced.trace.template.code.AbstractTemplate
import hello.advanced.trace.template.code.SubClassLogic1
import hello.advanced.trace.template.code.SubClassLogic2
import hello.advanced.util.logger
import org.junit.jupiter.api.Test

class TemplateMethodTest {

    private val log = logger()

    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }

    private fun logic1(){
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행")
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }

    private fun logic2(){
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행")
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    fun templateMethodV1(){
        val template1: AbstractTemplate = SubClassLogic1()
        template1.execute()
        val template2: AbstractTemplate = SubClassLogic2()
        template2.execute()
    }

}