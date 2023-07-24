package com.gradlekotlin.gdl_ktl.repositories

import com.gradlekotlin.gdl_ktl.entities.Orders
import org.springframework.data.repository.CrudRepository

interface OrderRepository: CrudRepository<Orders, Long> {
}