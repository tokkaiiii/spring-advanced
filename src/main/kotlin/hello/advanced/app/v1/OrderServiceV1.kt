package hello.advanced.app.v1

import org.springframework.stereotype.Service

@Service
class OrderServiceV1(
    private val orderRepository: OrderRepositoryV1
) {

    fun orderItem(itemId: String){
        orderRepository.save(itemId)
    }

}