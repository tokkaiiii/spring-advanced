package hello.advanced.trace.strategy.code.strategy

import hello.advanced.util.logger

/**
 * 전략을 파라미터로 전달 받는 방식
 */
class ContextV2{

    private val log = logger<ContextV2>()

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        strategy.call() // 위임
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }
}