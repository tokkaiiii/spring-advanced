package hello.advanced.trace.strategy.code.template

import hello.advanced.util.logger

class TimeLogTemplate {

    private val log = logger<TimeLogTemplate>()

    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        callback.call() // 위임
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }
}