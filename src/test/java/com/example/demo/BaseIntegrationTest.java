package com.example.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@ActiveProfiles("test")
public class BaseIntegrationTest {
    private static final String ELASTICSEARCH_VERSION = "7.17.8";

    @Container
    public static ElasticsearchContainer container = new ElasticsearchContainer(DockerImageName
            .parse("docker.elastic.co/elasticsearch/elasticsearch")
            .withTag(ELASTICSEARCH_VERSION)).withStartupTimeout(Duration.ofMinutes(5)).withExposedPorts(9200);

    @BeforeAll
    static void setUp() {
        container.start();
        System.setProperty("elasticsearch.host", container.getHost());
        System.setProperty("elasticsearch.port", String.valueOf(container.getMappedPort(9200)));
        assertTrue(container.isRunning());
    }

    @AfterAll
    static void destroy() {
        container.stop();
    }
    @Test
    public void loadContext(){

    }



}
