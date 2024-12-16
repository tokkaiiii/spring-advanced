package hello.advanced.trace.strategy.code.strategy

import hello.advanced.util.logger

class StrategyLogic2 : Strategy {

    private val log = logger<StrategyLogic2>()

    override fun call() {
        log.info("비즈니스 로직2 실행")
    }
}