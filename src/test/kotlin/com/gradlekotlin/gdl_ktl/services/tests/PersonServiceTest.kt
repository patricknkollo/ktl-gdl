package com.gradlekotlin.gdl_ktl.services.tests

import com.gradlekotlin.gdl_ktl.entities.Person
import com.gradlekotlin.gdl_ktl.repositories.PersonRepository
import com.gradlekotlin.gdl_ktl.services.PersonService
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class PersonServiceTest {

    val mockPersonRepository = Mockito.mock(PersonRepository::class.java)
    val service = PersonService(mockPersonRepository)

    private var person1: Person = Person(3,"patrick", "nkollo", "pnkollo@mail.com", "beckerskamp",17, "45276", "Essen" )
    private var person2: Person = Person(1,"elissa", "nkollo", "enkollo@mail.com", "beckerskamp",17, "45276", "Essen")
    private var person3: Person = Person(5,"alexandre", "nkollo", "ankollo@mail.com", "beckerskamp",17, "45276", "Essen")
    @Test
    @Order(1)
    fun test_createOrder(){
        var expected : ResponseEntity<Person> = ResponseEntity(person1, HttpStatus.OK)
        Mockito.`when`(mockPersonRepository.save(person1)).thenReturn(person1)
        val result : ResponseEntity<Person> = service.createUser(person1)
        Assert.assertEquals(expected.body, result.body)
    }

    @Test
    fun test_getAllTheOrderFromTheDatabase(){
        var expected : List<Person> = listOf(person2)
        Mockito.`when`(mockPersonRepository.findAll().toList()).thenReturn(listOf(person2))
        val result : List<Person> = service.getAllUsers()
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_getOrderById(){
        var expected : ResponseEntity<Person> = ResponseEntity(person2, HttpStatus.OK)
        Mockito.`when`(mockPersonRepository.findById(1L)).thenReturn(Optional.of(person2))
        val result : ResponseEntity<Person> = service.getUserById(1L)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_getOrderById2(){
        var expected : ResponseEntity<Person> = ResponseEntity(HttpStatus.NOT_FOUND)
        Mockito.`when`(mockPersonRepository.findById(1L)).thenReturn(Optional.empty())
        val result : ResponseEntity<Person> = service.getUserById(1L)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_deleteOrderById(){
        Mockito.`when`(mockPersonRepository.findById(1L)).thenReturn(Optional.of(person1))
        service.deletePersonById(1L)
        Mockito.verify(mockPersonRepository).deleteById(1L)

    }

    @Test
    fun test_updateOrderById(){
        Mockito.`when`(mockPersonRepository.findById(1L)).thenReturn(Optional.of(person3))
        Mockito.`when`(mockPersonRepository.save(person3)).thenReturn(person3)
        var result: ResponseEntity<Person> = service.updatePerson(1L, person1)
        Assertions.assertEquals(5, result.body?.personid)
    }
}