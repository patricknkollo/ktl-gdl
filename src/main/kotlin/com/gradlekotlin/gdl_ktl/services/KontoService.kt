package com.gradlekotlin.gdl_ktl.services

import com.gradlekotlin.gdl_ktl.entities.Konto
import com.gradlekotlin.gdl_ktl.repositories.KontoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class KontoService (@Autowired private val repository: KontoRepository) {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun getAllTheKontoFromTheDatabase(): List<Konto> {
        return repository.findAll().toList()
    }

    fun createKonto(konto: Konto): ResponseEntity<Konto> {
        val createdKonto = repository.save(konto)
        return ResponseEntity(createdKonto, HttpStatus.CREATED)
        logger.info("new account create : {}", konto.kontonr)
    }

    fun getKontoById(kontoId: Long): ResponseEntity<Konto> {
        var kontoOptional: Optional<Konto> = repository.findById(kontoId)
        if (kontoOptional.isPresent) {
            return ResponseEntity(kontoOptional.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    fun deleteKontoById(kontoeId: Long) {
        var optionalKonto: Optional<Konto> = repository.findById(kontoeId)
        if (optionalKonto.isPresent) {
            repository.deleteById(kontoeId)
        }
    }

    fun updateKontoById(kontoId: Long, newKonto: Konto): ResponseEntity<Konto> {
        var kontoOptional: Optional<Konto> = repository.findById(kontoId)
        if (kontoOptional.isPresent) {
            kontoOptional.get().guthaben = newKonto.guthaben
            kontoOptional.get().personid = newKonto.personid
            kontoOptional.get().kontonr= newKonto.kontonr

            repository.save(kontoOptional.get())
            logger.info("Order {} saved!", kontoOptional.get().toString())
            return ResponseEntity(kontoOptional.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}