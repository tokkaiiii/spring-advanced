package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import hello.advanced.util.logger
import org.junit.jupiter.api.Test

class ContextV1Test {

    private val log = logger<ContextV1Test>()

    @Test
    fun strategyV0() {
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
     * 전략 패턴 사용
     */
    @Test
    fun strategyV1(){
        val strategyLogic1 = StrategyLogic1()
        val context1 = ContextV1(strategyLogic1)
        context1.execute()

        val strategyLogic2 = StrategyLogic2()
        val context2 = ContextV1(strategyLogic2)
        context2.execute()
    }

}