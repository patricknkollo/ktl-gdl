package com.gradlekotlin.gdl_ktl.repositories

import com.gradlekotlin.gdl_ktl.entities.Konto
import org.springframework.data.repository.CrudRepository

interface KontoRepository : CrudRepository<Konto, Long>{
}
