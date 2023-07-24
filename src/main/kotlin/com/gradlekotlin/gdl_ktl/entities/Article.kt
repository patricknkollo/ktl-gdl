
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
class Article (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     var articleid: Long,
     var articlename: String,
     var articlebezeichnung: String,
     var articlepreis: Double,
)
