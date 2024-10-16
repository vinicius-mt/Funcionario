package com.empresa.funcionario.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SuppressWarnings("squid:S2187")
@Testcontainers
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.datasource.url=jdbc:tc:postgresql:11-alpine:///integration-tests-db",
                "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver",
                "spring.jpa.show-sql=true",
                "legacy.database.enabled=false",
                "flyway.url=jdbc:tc:postgresql:11-alpine:///integration-tests-db",
        }
)
public class PostgreSqlContainerTest {
}
