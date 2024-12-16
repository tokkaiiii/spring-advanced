package hello.advanced.trace.strategy.code.strategy

import hello.advanced.util.logger

class StrategyLogic1 : Strategy {

    private val log = logger<StrategyLogic1>()

    override fun call() {
        log.info("비즈니스 로직1 실행")
    }
}