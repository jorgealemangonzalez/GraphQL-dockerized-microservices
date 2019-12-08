package com.graphql.authors

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AuthorsServer

fun main(args: Array<String>) {
	SpringApplication.run(AuthorsServer::class.java, *args)
}
