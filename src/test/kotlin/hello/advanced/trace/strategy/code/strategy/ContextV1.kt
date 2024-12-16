package hello.advanced.trace.strategy.code.strategy

import hello.advanced.util.logger

/**
 * 필드에 전략을 보관하는 방식
 */
class ContextV1(
    private val strategy: Strategy
) {

    private val log = logger<ContextV1>()

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        strategy.call() // 위임
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }
}