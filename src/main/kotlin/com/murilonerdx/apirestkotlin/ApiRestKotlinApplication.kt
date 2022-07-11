package com.murilonerdx.apirestkotlin

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories("com.murilonerdx.apirestkotlin.repository")
@SpringBootApplication
class ApiRestKotlinApplication

fun main(args: Array<String>) {
    runApplication<ApiRestKotlinApplication>(*args)
}
