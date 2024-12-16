package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.*
import hello.advanced.util.logger
import org.junit.jupiter.api.Test

class ContextV2Test {

    private val log = logger<ContextV2Test>()

    @Test
    fun strategyV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행")
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }

    private fun logic2() {
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
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val context1 = ContextV2()
        context1.execute(strategyLogic1)

        val strategyLogic2 = StrategyLogic2()
        val context2 = ContextV2()
        context2.execute(strategyLogic2)
    }

    @Test
    fun strategyV2() {
        object : Strategy {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        }.also { ContextV2().execute(it) }

        object : Strategy {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        }.also { ContextV2().execute(it) }
    }

    @Test
    fun strategyV3() {
        ContextV2().execute(object : Strategy {
            override fun call() {
                log.info("비즈니스 로직1 실행")
            }
        })
        ContextV2().execute(object : Strategy {
            override fun call() {
                log.info("비즈니스 로직2 실행")
            }
        })
    }

}