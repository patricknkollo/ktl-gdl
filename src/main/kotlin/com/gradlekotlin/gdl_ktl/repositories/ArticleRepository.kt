
package com.gradlekotlin.gdl_ktl.repositories

import com.gradlekotlin.gdl_ktl.entities.Article
import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {
}
