package com.gradlekotlin.gdl_ktl.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
class Orders(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     var orderid: Long,
     var personid: Long,
     //var articles: List<Article>
)