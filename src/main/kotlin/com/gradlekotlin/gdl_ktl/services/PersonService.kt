package com.gradlekotlin.gdl_ktl.services

import com.gradlekotlin.gdl_ktl.entities.Person
import com.gradlekotlin.gdl_ktl.repositories.PersonRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService (@Autowired private val repository: PersonRepository){

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun getAllUsers(): List<Person> =
        repository.findAll().toList()

    fun createUser(person: Person): ResponseEntity<Person> {
        val createdUser = repository.save(person)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
        logger.info("new person create : {}", person.name)
    }

    fun getUserById( userId: Long): ResponseEntity<Person> {
        val user = repository.findById(userId).orElse(null)
        return if (user != null) ResponseEntity(user, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    fun deletePersonById(personid : Long) {
        repository.deleteById(personid);
        logger.info("the person with the id {} has been deleted", personid)
    }

    fun updatePerson(id:Long, newPerson:Person) : ResponseEntity<Person>{
        var optionalPerson: Optional<Person> = repository.findById(id)
        optionalPerson.get().email = newPerson.email
        optionalPerson.get().name = newPerson.name
        repository.save(optionalPerson.get())

        return ResponseEntity(optionalPerson.get(), HttpStatus.OK)
    }
}