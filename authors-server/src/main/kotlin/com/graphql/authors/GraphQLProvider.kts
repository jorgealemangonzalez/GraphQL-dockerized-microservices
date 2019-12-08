package com.graphql.authors

import com.google.common.base.Charsets
import com.google.common.io.Resources
import graphql.GraphQL
import graphql.schema.GraphQLSchema
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class GraphQLProvider {

    private var graphQL: GraphQL? = null

    @Autowired
    lateinit var graphQLDataFetchers: GraphQLDataFetchers.GraphQLDataFetchers

    @Bean
    fun graphQL(): GraphQL? {
        println("Initializing GraphQL")
        return graphQL
    }

    @PostConstruct
    fun init() {
        val url = Resources.getResource("schema.graphqls")
        val sdl = Resources.toString(url, Charsets.UTF_8)
        val graphQLSchema = buildSchema(sdl)
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildSchema(sdl: String): GraphQLSchema {
        val typeRegistry = SchemaParser().parse(sdl)
        val runtimeWiring = buildWiring()
        val schemaGenerator = SchemaGenerator()
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }

    private fun buildWiring(): RuntimeWiring {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", graphQLDataFetchers.bookByIdDataFetcher))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.authorDataFetcher)
                        .dataFetcher("pageCount", graphQLDataFetchers.pageCountDataFetcher))
                .build()
    }
}
