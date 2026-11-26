
package com.ramadan.urlshortener.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ShortenedUrlPersistenceTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldPersistShortenedUrl() {
        // Arrange
        ShortenedUrl url = new ShortenedUrl(
                "https://spring.io",
                "abc123"
        );

        // Act
        entityManager.persist(url);
        entityManager.flush();

        // Assert
        assertThat(url.getId()).isNotNull();
        assertThat(url.getOriginalUrl()).isEqualTo("https://spring.io");
        assertThat(url.getShortCode()).isEqualTo("abc123");
        assertThat(url.getCreatedAt()).isNotNull();
        assertThat(url.getClickCount()).isZero();
        assertThat(url.getExpiresAt()).isNull();
    }
}
