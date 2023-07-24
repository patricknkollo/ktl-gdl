package com.gradlekotlin.gdl_ktl.controllers

import com.gradlekotlin.gdl_ktl.entities.Orders
import com.gradlekotlin.gdl_ktl.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/api/orders")
class OrderController (@Autowired private val  service: OrderService) {

    @GetMapping("/order/all")
    @ResponseBody
    fun getAllOrders(): List<Orders> =
        service.getAllTheOrderFromTheDatabase().toList()

    @PostMapping("/save/order")
    @ResponseBody
    fun createOrder(@RequestBody orders: Orders): ResponseEntity<Orders> { return service.createOrder(orders) }

    @GetMapping("/order/{id}")
    fun getOrderById(@PathVariable("id") orderId: Long): ResponseEntity<Orders> {
        return service.getOrderById(orderId)
    }

    @GetMapping("/update/order/{id}")
    fun updateOrderById(@PathVariable("id") orderId: Long, orders: Orders): ResponseEntity<Orders> {
        return service.updateOrderById(orderId, orders)
    }

    @DeleteMapping("/delete/order/{id}")
    fun deleteOrderById(@PathVariable("id") orderId: Long) {
        service.deleteOrderById(orderId)
    }
}