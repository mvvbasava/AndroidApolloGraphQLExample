#!/usr/bin/env kotlin

/**
 * Script to generate Apollo GraphQL code
 * Run this script to generate Kotlin classes from GraphQL schema and queries
 * 
 * Usage: ./gradlew generateApolloSources
 */

println("Apollo GraphQL code generation script")
println("To generate Apollo code, run: ./gradlew generateApolloSources")
println("Make sure you have:")
println("1. GraphQL schema file at: app/src/main/graphql/schema.graphqls")
println("2. GraphQL query files in: app/src/main/graphql/")
println("3. Apollo plugin configured in app/build.gradle")
