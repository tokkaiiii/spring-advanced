package hello.advanced.trace.threadlocal.code

import hello.advanced.util.logger
import java.lang.Thread.sleep

class ThreadLocalService {

    private val log = logger<ThreadLocalService>()

    private var nameStore = ThreadLocal<String>()

    fun logic(name: String): String{
        log.info("저장 name=$name -> nameStore=${nameStore.get()}")
        nameStore.set(name)
        sleep(1000)
        log.info("조회 nameStore=${nameStore.get()}")
        return nameStore.get()
    }

}