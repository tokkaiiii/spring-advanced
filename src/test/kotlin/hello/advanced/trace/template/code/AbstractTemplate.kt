package hello.advanced.trace.template.code

import hello.advanced.util.logger

abstract class AbstractTemplate {

    private val log = logger()

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        call() // 상속
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info("resultTime=$resultTime")
    }

    abstract fun call()

}