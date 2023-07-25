package com.gradlekotlin.gdl_ktl.services.tests

import com.gradlekotlin.gdl_ktl.entities.Orders
import com.gradlekotlin.gdl_ktl.repositories.OrderRepository
import com.gradlekotlin.gdl_ktl.services.OrderService
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class OrdersServiceTest {

    val mockOrdersRepository = Mockito.mock(OrderRepository::class.java)
    val service = OrderService(mockOrdersRepository)

    private var order1: Orders = Orders(3,4)
    private var order2: Orders = Orders(1,6)
    private var order3: Orders = Orders(5,9)
    @Test
    @Order(1)
    fun test_createOrder(){
        var expected : ResponseEntity<Orders> = ResponseEntity(order1, HttpStatus.OK)
        Mockito.`when`(mockOrdersRepository.save(order1)).thenReturn(order1)
        val result : ResponseEntity<Orders> = service.createOrder(order1)
        Assert.assertEquals(expected.body, result.body)
    }

    @Test
    fun test_getAllTheOrderFromTheDatabase(){
        var expected : List<Orders> = listOf(order2)
        Mockito.`when`(mockOrdersRepository.findAll().toList()).thenReturn(listOf(order2))
        val result : List<Orders> = service.getAllTheOrderFromTheDatabase()
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_getOrderById(){
        var expected : ResponseEntity<Orders> = ResponseEntity(order2, HttpStatus.OK)
        Mockito.`when`(mockOrdersRepository.findById(1L)).thenReturn(Optional.of(order2))
        val result : ResponseEntity<Orders> = service.getOrderById(1L)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_getOrderById2(){
        var expected : ResponseEntity<Orders> = ResponseEntity(HttpStatus.NOT_FOUND)
        Mockito.`when`(mockOrdersRepository.findById(1L)).thenReturn(Optional.empty())
        val result : ResponseEntity<Orders> = service.getOrderById(1L)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_deleteOrderById(){
        Mockito.`when`(mockOrdersRepository.findById(1L)).thenReturn(Optional.of(order1))
        service.deleteOrderById(1L)
        Mockito.verify(mockOrdersRepository).deleteById(1L)

    }

    @Test
    fun test_updateOrderById(){
        Mockito.`when`(mockOrdersRepository.findById(1L)).thenReturn(Optional.of(order3))
        Mockito.`when`(mockOrdersRepository.save(order3)).thenReturn(order3)
        var result: ResponseEntity<Orders> = service.updateOrderById(1L, order1)
        Assertions.assertEquals(4, result.body?.personid)
    }
}