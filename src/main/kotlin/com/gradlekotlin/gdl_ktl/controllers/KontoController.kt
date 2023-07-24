package com.gradlekotlin.gdl_ktl.controllers

import com.gradlekotlin.gdl_ktl.entities.Konto
import com.gradlekotlin.gdl_ktl.services.KontoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/konto")
class KontoController (@Autowired private val  service: KontoService){

    @GetMapping("/kontos")
    @ResponseBody
    fun getAllUsers(): List<Konto> =
        service.getAllTheKontoFromTheDatabase().toList()

    @PostMapping("/save/konto")
    @ResponseBody
    fun createKonto(@RequestBody konto: Konto): ResponseEntity<Konto> { return service.createKonto(konto) }

    @GetMapping("/konto/{id}")
    fun getKontoById(@PathVariable("id") kontoId: Long): ResponseEntity<Konto> {
        return service.getKontoById(kontoId)
    }

    @GetMapping("/update/konto/{id}")
    fun updateKontoById(@PathVariable("id") kontoId: Long, konto: Konto): ResponseEntity<Konto> {
        return service.updateKontoById(kontoId, konto)
    }

    @DeleteMapping("/delete/konto/{id}")
    fun deleteKontoById(@PathVariable("id") kontoId: Long) {
        service.deleteKontoById(kontoId)
    }
}