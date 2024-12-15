package hello.advanced.trace.threadlocal

import hello.advanced.trace.threadlocal.code.FieldService
import hello.advanced.util.logger
import org.junit.jupiter.api.Test
import java.lang.Thread.*

class FieldServiceTest {

    private val log = logger<FieldServiceTest>()
    private val fieldService = FieldService()

    @Test
    fun field(){
        log.info("main start")
        val userA = Runnable { fieldService.logic("userA") }
        val userB = Runnable { fieldService.logic("userB") }
        val threadA = Thread(userA)
        threadA.setName("thread-A")
        val threadB = Thread(userB)
        threadB.setName("thread-B")

        threadA.start()
//        sleep(2000) // 동시성 문제 발생 X
        sleep(100) // 동시성 문제 발생 O
        threadB.start()
        threadB.join()
        log.info("main exit")

    }

}