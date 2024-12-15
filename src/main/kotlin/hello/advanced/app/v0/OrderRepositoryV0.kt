package hello.advanced.app.v0

import org.springframework.stereotype.Repository
import java.lang.Thread.sleep

@Repository
class OrderRepositoryV0 {

    fun save(itemId: String){
        // 저장 로직
        if (itemId == "ex"){
            throw IllegalStateException("예외 발생!")
        }
        sleep(1000)
    }

}