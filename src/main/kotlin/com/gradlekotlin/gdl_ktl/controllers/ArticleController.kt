
package com.gradlekotlin.gdl_ktl.controllers

import com.gradlekotlin.gdl_ktl.entities.Article
import com.gradlekotlin.gdl_ktl.services.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/api/articles")
class ArticleController (@Autowired private val  service: ArticleService) {

    @GetMapping("/all")
    @ResponseBody
    fun getAllArticles(): List<Article> =
        service.getAllTheArticleFromTheDatabase().toList()


    @PostMapping("/save/article")
    @ResponseBody
    fun createArticle(@RequestBody article: Article): ResponseEntity<Article> { return service.createArticle(article) }

    @GetMapping("/{id}")
    fun getArticleById(@PathVariable("id") articleId: Long): ResponseEntity<Article> {
        return service.getArticleById(articleId)
    }

    @GetMapping("/update/article/{id}")
    fun updateArticleById(@PathVariable("id") articleId: Long, article: Article): ResponseEntity<Article> {
        return service.updateArticleById(articleId, article)
    }

    @DeleteMapping("/delete/article/{id}")
    fun deleteArticleById(@PathVariable("id") articleId: Long) {
        service.deleteArticleById(articleId)
    }
}
