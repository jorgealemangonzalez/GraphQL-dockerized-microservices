import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		jcenter()
		mavenCentral()
	}
	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.21")
		"classpath"(group= "com.apollographql.apollo", name= "apollo-gradle-plugin", version= "1.2.2")
	}
}

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("java")
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	id("com.apollographql.android") version "1.2.2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

springBoot {
	mainClassName = "com.graphql.authors.AuthorsServerKt"
}

repositories {
	mavenCentral()
	jcenter() // This is for apollographql
}

dependencies {

	implementation("com.graphql-java:graphql-java:11.0")
	implementation("com.graphql-java:graphql-java-spring-boot-starter-webmvc:1.0")
	implementation("com.google.guava:guava:26.0-jre")
	implementation("org.springframework.boot:spring-boot-starter-web:2.1.8.RELEASE")
	implementation("org.springframework.cloud:spring-cloud-starter-config:2.1.4.RELEASE")
	implementation("com.apollographql.apollo:apollo-runtime:1.2.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// If not already on your classpath, you might need the jetbrains annotations
	compileOnly("org.jetbrains:annotations:13.0")
	testCompileOnly("org.jetbrains:annotations:13.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	compile(kotlin("script-runtime"))
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

apollo {
	generateKotlinModels = false // or false
}
