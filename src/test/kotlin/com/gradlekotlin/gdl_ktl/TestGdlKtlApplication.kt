package com.gradlekotlin.gdl_ktl

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestGdlKtlApplication

fun main(args: Array<String>) {
	fromApplication<GdlKtlApplication>().with(TestGdlKtlApplication::class).run(*args)
}
