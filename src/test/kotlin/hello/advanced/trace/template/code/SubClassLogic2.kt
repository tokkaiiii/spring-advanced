package hello.advanced.trace.template.code

import hello.advanced.util.logger

class SubClassLogic2 : AbstractTemplate() {

    private val log = logger<SubClassLogic2>()

    override fun call() {
        log.info("비즈니스 로직2 실행")
    }
}