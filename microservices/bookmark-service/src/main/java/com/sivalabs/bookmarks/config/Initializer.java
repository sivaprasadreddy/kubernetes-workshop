package com.sivalabs.bookmarks.config;

import com.sivalabs.bookmarks.domain.Bookmark;
import com.sivalabs.bookmarks.domain.BookmarkRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Initializer implements CommandLineRunner {

    private final BookmarkRepository bookmarkRepository;

    @Override
    public void run(String... args) {
        log.info("Running Initializer.....");
        Bookmark p1 =
                new Bookmark(
                        null,
                        "SpringBoot Integration Testing using TestContainers Starter",
                        "https://sivalabs.in/2020/02/spring-boot-integration-testing-using-testcontainers-starter/",
                        LocalDateTime.now(),
                        null);
        Bookmark p2 =
                new Bookmark(
                        null,
                        "How to teach Git",
                        "https://rachelcarmena.github.io/2018/12/12/how-to-teach-git.html",
                        LocalDateTime.now(),
                        null);
        Bookmark p3 =
                new Bookmark(
                        null,
                        "Continuous Integration of Java project with GitHub Actions",
                        "https://medium.com/faun/continuous-integration-of-java-project-with-github-actions-7a8a0e8246ef",
                        LocalDateTime.now(),
                        null);
        Bookmark p4 =
                new Bookmark(
                        null,
                        "Java Microservices: A Practical Guide",
                        "https://www.marcobehler.com/guides/java-microservices-a-practical-guide",
                        LocalDateTime.now(),
                        null);
        Bookmark p5 =
                new Bookmark(
                        null,
                        "All You Need To Know About Unit Testing with Spring Boot",
                        "https://reflectoring.io/unit-testing-spring-boot/",
                        LocalDateTime.now(),
                        null);

        bookmarkRepository.saveAll(List.of(p1, p2, p3, p4, p5));
    }
}
