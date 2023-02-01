plugins {
	java
	`jvm-test-suite`
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.shakers"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}

}



repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	compileOnly("org.springframework.boot:spring-boot-starter-logging")

	compileOnly("org.jetbrains:annotations:16.0.2")


	implementation("org.javamoney:moneta:1.4.2")


	annotationProcessor("org.projectlombok:lombok")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.named("check") {
	dependsOn(testing.suites.named("integrationTest"))
}

testing {
	suites {

		val test by getting(JvmTestSuite::class)

		configureEach {
			if (this is JvmTestSuite) {
				useJUnitJupiter()
				dependencies {
					implementation("org.mockito:mockito-junit-jupiter:4.6.1")
					implementation("io.projectreactor:reactor-test")
					implementation("com.tngtech.junit.dataprovider:junit-jupiter-params-dataprovider:2.10")
					compileOnly("org.springframework.boot:spring-boot-starter-webflux")

				}
			}
		}

		val integrationTest by registering(JvmTestSuite::class) {
			dependencies {
				implementation(project())
				implementation("org.junit.platform:junit-platform-suite-engine:1.8.1")
				implementation("org.springframework.boot:spring-boot-starter-test")


			}
			targets {
				all {
					testTask.configure{
						shouldRunAfter(test)
					}
				}
			}
			sources {
				java {
					setSrcDirs(listOf("src/integrationTest/java"))
				}
			}
		}
	}
}
