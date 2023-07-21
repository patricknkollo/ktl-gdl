package com.gradlekotlin.gdl_ktl.repositories

import com.gradlekotlin.gdl_ktl.entities.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository : CrudRepository<Person, Long> {
}