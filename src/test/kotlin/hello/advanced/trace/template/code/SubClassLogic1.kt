package hello.advanced.trace.template.code

import hello.advanced.util.logger

class SubClassLogic1 : AbstractTemplate() {

    private val log = logger<SubClassLogic1>()

    override fun call() {
        log.info("비즈니스 로직1 실행")
    }
}