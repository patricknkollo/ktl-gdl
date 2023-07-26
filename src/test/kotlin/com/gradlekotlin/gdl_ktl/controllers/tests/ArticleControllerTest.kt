package com.gradlekotlin.gdl_ktl.controllers.tests

import com.gradlekotlin.gdl_ktl.controllers.ArticleController
import com.gradlekotlin.gdl_ktl.entities.Article
import com.gradlekotlin.gdl_ktl.services.ArticleService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ArticleControllerTest {

    val service = Mockito.mock(ArticleService::class.java)
    val controller = ArticleController(service)

    private var article1 : Article = Article(1L,"nike", "HUA23", 85.99)
    private var article2 : Article = Article(1L,"nike", "HUA23", 85.99)

    @Test
    fun test_getAllUsers(){
        Mockito.`when`(service.getAllTheArticleFromTheDatabase()).thenReturn(listOf(article1, article2))
        var result : List<Article> = controller.getAllArticles()
        Assertions.assertEquals(article1, result.get(0))
    }

    @Test
    fun test_createArticle(){
        Mockito.`when`(service.createArticle(article1)).thenReturn(ResponseEntity(article2,HttpStatus.OK))
        var result : ResponseEntity<Article> = controller.createArticle(article1)
        Assertions.assertEquals(article2, result.body)
    }

    @Test
    fun test_getArticleById(){
        Mockito.`when`(service.getArticleById(1L)).thenReturn(ResponseEntity(article2, HttpStatus.OK))
        var result: ResponseEntity<Article> = controller.getArticleById(1L)
        Assertions.assertEquals(article2, result.body)
    }

    @Test
    fun test_updateArticleById(){
        Mockito.`when`(service.updateArticleById(2L, article1)).thenReturn(ResponseEntity(article2, HttpStatus.OK))
        var result : ResponseEntity<Article> = controller.updateArticleById(2L, article1)
        Assertions.assertEquals(article2, result.body)
    }

    @Test
    fun test_deleteArticleById(){
        controller.deleteArticleById(1L)
        Mockito.verify(service, Mockito.times(1)).deleteArticleById(1L)
    }
}