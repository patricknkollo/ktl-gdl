package com.gradlekotlin.gdl_ktl.services.tests

import com.gradlekotlin.gdl_ktl.entities.Article
import com.gradlekotlin.gdl_ktl.entities.Orders
import com.gradlekotlin.gdl_ktl.repositories.ArticleRepository
import com.gradlekotlin.gdl_ktl.services.ArticleService
import org.junit.Assert
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*

class ArticleServiceTest {

    val mockArticleRepository = Mockito.mock(ArticleRepository::class.java)
    val service = ArticleService(mockArticleRepository)

    private var article1: Article = Article(3,"Nike Sneakers", "HUARache", 19.99)
    private var article2: Article = Article(1,"Adidas jaket", "AD_Jkt", 29.99)

    @Test
    @Order(1)
    fun test_createOrder(){
        var expected : ResponseEntity<Article> = ResponseEntity(article1, HttpStatus.OK)
        Mockito.`when`(mockArticleRepository.save(article1)).thenReturn(article1)
        val result : ResponseEntity<Article> = service.createArticle(article1)
        Assert.assertEquals(expected.body, result.body)
    }

    @Test
    fun test_getAllTheOrderFromTheDatabase(){
        var expected : List<Article> = listOf(article2)
        Mockito.`when`(mockArticleRepository.findAll().toList()).thenReturn(listOf(article2))
        val result : List<Article> = service.getAllTheArticleFromTheDatabase()
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_getOrderById(){
        var expected : ResponseEntity<Article> = ResponseEntity(article2, HttpStatus.OK)
        Mockito.`when`(mockArticleRepository.findById(1L)).thenReturn(Optional.of(article2))
        val result : ResponseEntity<Article> = service.getArticleById(1L)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_getOrderById2(){
        var expected : ResponseEntity<Orders> = ResponseEntity(HttpStatus.NOT_FOUND)
        Mockito.`when`(mockArticleRepository.findById(1L)).thenReturn(Optional.empty())
        val result : ResponseEntity<Article> = service.getArticleById(1L)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun test_deleteOrderById(){
        Mockito.`when`(mockArticleRepository.findById(1L)).thenReturn(Optional.of(article1))
        service.deleteArticleById(1L)
        Mockito.verify(mockArticleRepository).deleteById(1L)

    }

    @Test
    fun test_updateOrderById(){
        Mockito.`when`(mockArticleRepository.findById(1L)).thenReturn(Optional.of(article2))
        Mockito.`when`(mockArticleRepository.save(article2)).thenReturn(article2)
        var result: ResponseEntity<Article> = service.updateArticleById(1L, article1)
        Assertions.assertEquals("HUARache", result.body?.articlebezeichnung)
    }
}