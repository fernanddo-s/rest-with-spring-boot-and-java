package com.example.project.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer<?> postgre = new PostgreSQLContainer<>("postgres:16.1");
        private static void startContaniners(){
            Startables.deepStart(Stream.of(postgre)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", postgre.getJdbcUrl(),
                    "spring.datasource.username", postgre.getUsername(),
                    "spring.datasource.password", postgre.getPassword()
            );
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContaniners();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource("testcontaniners", (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainers);
        }


    }
}
