package hello.advanced.trace.threadlocal.code

import hello.advanced.util.logger
import java.lang.Thread.sleep

class FieldService {

    private val log = logger<FieldService>()

    private var nameStore: String? = null

    fun logic(name: String): String{
        log.info("저장 name=$name -> nameStore=$nameStore")
        nameStore = name
        sleep(1000)
        log.info("조회 nameStore=$nameStore")
        return nameStore!!
    }

}