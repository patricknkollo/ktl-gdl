package com.gradlekotlin.gdl_ktl.services

import com.gradlekotlin.gdl_ktl.entities.Orders
import com.gradlekotlin.gdl_ktl.repositories.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService (@Autowired private val repository: OrderRepository){

    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun getAllTheOrderFromTheDatabase(): List<Orders>{
        return repository.findAll().toList()
    }

    fun createOrder(orders: Orders): ResponseEntity<Orders> {
        val createdOrder = repository.save(orders)
        return ResponseEntity(createdOrder, HttpStatus.CREATED)
        logger.info("new person create : {}", orders.orderid)
    }

    fun getOrderById ( articleId: Long): ResponseEntity<Orders> {
        var optionalOrders: Optional<Orders> = repository.findById(articleId)
        if(optionalOrders.isPresent){
            return ResponseEntity(optionalOrders.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    fun deleteOrderById (OrdereId: Long){
        var optionalArticle: Optional<Orders> = repository.findById(OrdereId)
        if(optionalArticle.isPresent){
            repository.deleteById(OrdereId)
        }
    }

    fun updateOrderById (OrdereId: Long, newOrders: Orders): ResponseEntity<Orders> {
        var optionalOrders: Optional<Orders> = repository.findById(OrdereId)
        if(optionalOrders.isPresent){
          //  optionalOrders.get().articles=newOrders.articles
            optionalOrders.get().personid=newOrders.personid

            repository.save(optionalOrders.get())
            logger.info("Order {} saved!", optionalOrders.get().toString())
            return ResponseEntity(optionalOrders.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}