package com.gradlekotlin.gdl_ktl.controllers.tests

import com.gradlekotlin.gdl_ktl.controllers.PersonController
import com.gradlekotlin.gdl_ktl.entities.Person
import com.gradlekotlin.gdl_ktl.services.PersonService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PersonControllerTest {

    val service = Mockito.mock(PersonService::class.java)
    val controller = PersonController(service)

    private var person1 : Person = Person(1L,"klaus", "nkollo", "nkollo@mail.com" ,"ndedistraße",5, "84635", "köln")
    private var person2 : Person = Person(1L,"patrick", "müller", "müller@mail.com","enanguestraße",85, "38452", "duisbuurg")

    @Test
    fun test_getAllUsers(){
        Mockito.`when`(service.getAllUsers()).thenReturn(listOf(person1, person2))
        var result : List<Person> = controller.getAllUsers()
        Assertions.assertEquals(person1, result.get(0))
    }

    @Test
    fun test_createArticle(){
        Mockito.`when`(service.createUser(person1)).thenReturn(ResponseEntity(person2, HttpStatus.OK))
        var result : ResponseEntity<Person> = controller.createUser(person1)
        Assertions.assertEquals(person2, result.body)
    }

    @Test
    fun test_getArticleById(){
        Mockito.`when`(service.getUserById(1L)).thenReturn(ResponseEntity(person2, HttpStatus.OK))
        var result: ResponseEntity<Person> = controller.getUserById(1L)
        Assertions.assertEquals(person2, result.body)
    }

    @Test
    fun test_updateArticleById(){
        Mockito.`when`(service.updatePerson(2L, person1)).thenReturn(ResponseEntity(person2, HttpStatus.OK))
        var result : ResponseEntity<Person> = controller.updateUserById(2L, person1)
        Assertions.assertEquals(person2, result.body)
    }

    @Test
    fun test_deleteArticleById(){
        controller.deleteUserById(1L)
        Mockito.verify(service, Mockito.times(1)).deletePersonById(1L)
    }
}