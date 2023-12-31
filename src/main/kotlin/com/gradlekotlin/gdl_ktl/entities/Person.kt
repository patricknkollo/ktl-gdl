package com.gradlekotlin.gdl_ktl.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Person (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var personid: Long,
    var vorname: String,
    var name: String,
    var email: String,
    var straße: String,
    var streetnr: Int,
    var plz: String,
    var stadt: String

    )