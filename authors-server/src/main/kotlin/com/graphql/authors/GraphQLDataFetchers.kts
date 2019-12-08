package com.graphql.authors

import com.graphql.authors.GraphQLDataFetchers.Book
import com.google.common.collect.ImmutableMap
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component
import java.util.*

typealias Book = Map<String, String>

@Component
class GraphQLDataFetchers {

    val bookByIdDataFetcher: DataFetcher<*> = DataFetcher { dataFetchingEnvironment ->
        val bookId: String = dataFetchingEnvironment.getArgument("id")
        books
                .stream()
                .filter { book -> book["id"] == bookId }
                .findFirst()
                .orElse(null)
    }

    val authorDataFetcher: DataFetcher<*> = DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
        val book: Book = dataFetchingEnvironment.getSource()
        val authorId = book["authorId"]
        authors
                .stream()
                .filter { author -> author["id"] == authorId }
                .findFirst()
                .orElse(null)
    }

    val pageCountDataFetcher: DataFetcher<*> = DataFetcher { dataFetchingEnvironment ->
        val book: Book = dataFetchingEnvironment.getSource()
        book.get("totalPages")
    }

    companion object {

        private val books = Arrays.asList<Book>(
                ImmutableMap.of("id", "book-1",
                        "name", "Harry Potter and the Philosopher's Stone",
                        "totalPages", "223",
                        "authorId", "author-1"),
                ImmutableMap.of("id", "book-2",
                        "name", "Moby Dick",
                        "totalPages", "635",
                        "authorId", "author-2"),
                ImmutableMap.of("id", "book-3",
                        "name", "Interview with the vampire",
                        "totalPages", "371",
                        "authorId", "author-3")
        )

        private val authors = Arrays.asList<Book>(
                ImmutableMap.of("id", "author-1",
                        "firstName", "Joanne",
                        "lastName", "Rowling"),
                ImmutableMap.of("id", "author-2",
                        "firstName", "Herman",
                        "lastName", "Melville"),
                ImmutableMap.of("id", "author-3",
                        "firstName", "Anne",
                        "lastName", "Rice")
        )
    }
}

