package com.gradlekotlin.gdl_ktl.controllers.tests

import com.gradlekotlin.gdl_ktl.controllers.KontoController
import com.gradlekotlin.gdl_ktl.entities.Konto
import com.gradlekotlin.gdl_ktl.services.KontoService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class KontoControllerTest {

    val service = Mockito.mock(KontoService::class.java)
    val controller = KontoController(service)

    private var konto1 : Konto = Konto(1L,2L, "534654", 8589.99)
    private var konto2 : Konto = Konto(2L,3L, "54545", 2085.99)

    @Test
    fun test_getAllUsers(){
        Mockito.`when`(service.getAllTheKontoFromTheDatabase()).thenReturn(listOf(konto1, konto2))
        var result : List<Konto> = controller.getAllKontos()
        Assertions.assertEquals(konto1, result.get(0))
    }

    @Test
    fun test_createArticle(){
        Mockito.`when`(service.createKonto(konto1)).thenReturn(ResponseEntity(konto2, HttpStatus.OK))
        var result : ResponseEntity<Konto> = controller.createKonto(konto1)
        Assertions.assertEquals(konto2, result.body)
    }

    @Test
    fun test_getArticleById(){
        Mockito.`when`(service.getKontoById(1L)).thenReturn(ResponseEntity(konto2, HttpStatus.OK))
        var result: ResponseEntity<Konto> = controller.getKontoById(1L)
        Assertions.assertEquals(konto2, result.body)
    }

    @Test
    fun test_updateArticleById(){
        Mockito.`when`(service.updateKontoById(2L, konto1)).thenReturn(ResponseEntity(konto2, HttpStatus.OK))
        var result : ResponseEntity<Konto> = controller.updateKontoById(2L, konto1)
        Assertions.assertEquals(konto2, result.body)
    }

    @Test
    fun test_deleteArticleById(){
        controller.deleteKontoById(1L)
        Mockito.verify(service, Mockito.times(1)).deleteKontoById(1L)
    }
}