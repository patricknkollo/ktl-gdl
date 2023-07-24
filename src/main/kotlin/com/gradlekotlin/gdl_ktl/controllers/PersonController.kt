package com.gradlekotlin.gdl_ktl.controllers

import com.gradlekotlin.gdl_ktl.entities.Person
import com.gradlekotlin.gdl_ktl.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/users")
class PersonController (@Autowired private val  service: PersonService) {

    @GetMapping("/users")
    @ResponseBody
    fun getAllUsers(): List<Person> =
        service.getAllUsers().toList()

    @PostMapping("/save/user")
    @ResponseBody
    fun createUser(@RequestBody person: Person): ResponseEntity<Person> { return service.createUser(person) }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: Long): ResponseEntity<Person> {
        return service.getUserById(userId)
    }

    @GetMapping("/update/user/{id}")
    fun updateUserById(@PathVariable("id") userId: Long, person: Person): ResponseEntity<Person> {
        return service.updatePerson(userId, person)
    }

    @DeleteMapping("/delete/user/{id}")
    fun deleteUserById(@PathVariable("id") userId: Long) {
         service.deletePersonById(userId)
    }
}