package com.gradlekotlin.gdl_ktl.controllers.tests

import com.gradlekotlin.gdl_ktl.controllers.OrderController
import com.gradlekotlin.gdl_ktl.entities.Orders
import com.gradlekotlin.gdl_ktl.services.OrderService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class OrderControllerTest {

    val service = Mockito.mock(OrderService::class.java)
    val controller = OrderController(service)

    private var order1 : Orders = Orders(1L,2)
    private var order2 : Orders = Orders(2L,1)

    @Test
    fun test_getAllUsers(){
        Mockito.`when`(service.getAllTheOrderFromTheDatabase()).thenReturn(listOf(order1, order2))
        var result : List<Orders> = controller.getAllOrders()
        Assertions.assertEquals(order1, result.get(0))
    }

    @Test
    fun test_createArticle(){
        Mockito.`when`(service.createOrder(order1)).thenReturn(ResponseEntity(order2, HttpStatus.OK))
        var result : ResponseEntity<Orders> = controller.createOrder(order1)
        Assertions.assertEquals(order2, result.body)
    }

    @Test
    fun test_getArticleById(){
        Mockito.`when`(service.getOrderById(1L)).thenReturn(ResponseEntity(order2, HttpStatus.OK))
        var result: ResponseEntity<Orders> = controller.getOrderById(1L)
        Assertions.assertEquals(order2, result.body)
    }

    @Test
    fun test_updateArticleById(){
        Mockito.`when`(service.updateOrderById(2L, order1)).thenReturn(ResponseEntity(order2, HttpStatus.OK))
        var result : ResponseEntity<Orders> = controller.updateOrderById(2L, order1)
        Assertions.assertEquals(order2, result.body)
    }

    @Test
    fun test_deleteArticleById(){
        controller.deleteOrderById(1L)
        Mockito.verify(service, Mockito.times(1)).deleteOrderById(1L)
    }
}