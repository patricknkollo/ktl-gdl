package com.gradlekotlin.gdl_ktl.services.tests

import com.gradlekotlin.gdl_ktl.entities.Konto
import com.gradlekotlin.gdl_ktl.repositories.KontoRepository
import com.gradlekotlin.gdl_ktl.services.KontoService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class KontoServiceTest {

    private val repository = Mockito.mock(KontoRepository:: class.java)
    private val service = KontoService(repository)

    private val konto1 : Konto = Konto(1, 3, "54516385", 2000.0)
    private var konto2 : Konto = Konto(2, 4, "96436955", 2000.0)

    @Test
    fun test_getAllTheKontoFromTheDatabase(){
        var expected:List<Konto> = listOf(konto1, konto2)
        Mockito.`when`(repository.findAll().toList()).thenReturn(expected)
        var kontos:List<Konto> = service.getAllTheKontoFromTheDatabase()
        Assertions.assertEquals(expected, kontos)

    }

    @Test
    fun test_createKonto(){
        Mockito.`when`(repository.save(konto1)).thenReturn(konto1)
        var konto: ResponseEntity<Konto> = service.createKonto(konto1)
        Assertions.assertEquals(konto1,konto.body)
    }

    @Test
    fun test_getKontoById1(){
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(konto1))
        var result: ResponseEntity<Konto> = service.getKontoById(1)
        Assertions.assertEquals(konto1, result.body)
    }

    @Test
    fun test_getKontoById2(){
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.empty())
        var result: ResponseEntity<Konto> = service.getKontoById(1)
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
    }

    @Test
    fun test_deleteKontoById(){
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(konto1))
        service.deleteKontoById(1L)
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L)
    }
    @Test
    fun test_updateKontoById1(){
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.of(konto2))
        Mockito.`when`(repository.save(konto2)).thenReturn(konto2)
        var result : ResponseEntity<Konto> = service.updateKontoById(1, konto1)
        Assertions.assertEquals(konto2, result.body)
    }

    @Test
    fun test_updateKontoById2(){
        Mockito.`when`(repository.findById(1)).thenReturn(Optional.empty())
        var result : ResponseEntity<Konto> = service.updateKontoById(1, konto1)
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
    }
}