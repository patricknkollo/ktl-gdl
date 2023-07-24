
package com.gradlekotlin.gdl_ktl.services

import com.gradlekotlin.gdl_ktl.entities.Article
import com.gradlekotlin.gdl_ktl.repositories.ArticleRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticleService ( @Autowired val repository: ArticleRepository) {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    fun getAllTheArticleFromTheDatabase(): List<Article>{
       return repository.findAll().toList()
    }

    fun createArticle(article: Article): ResponseEntity<Article> {
        val createdArticle = repository.save(article)
        return ResponseEntity(createdArticle, HttpStatus.CREATED)
        logger.info("new article create : {}, bezeichnung : {}", article.articlename, article.articlebezeichnung)
    }

    fun getArticleById ( articleId: Long): ResponseEntity<Article>{
        var optionalArticle: Optional<Article> = repository.findById(articleId)
        if(optionalArticle.isPresent){
            return ResponseEntity(optionalArticle.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    fun deleteArticleById ( articleId: Long){
        var optionalArticle: Optional<Article> = repository.findById(articleId)
        if(optionalArticle.isPresent){
            repository.deleteById(articleId)
        }
    }

    fun updateArticleById ( articleId: Long, newArticle: Article): ResponseEntity<Article>{
        var optionalArticle: Optional<Article> = repository.findById(articleId)
        if(optionalArticle.isPresent){
            optionalArticle.get().articlebezeichnung=newArticle.articlebezeichnung
            optionalArticle.get().articlename=newArticle.articlename
            optionalArticle.get().articlepreis=newArticle.articlepreis

            repository.save(optionalArticle.get())
            logger.info("article {} saved!", optionalArticle.get().toString())
            return ResponseEntity(optionalArticle.get(), HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}
