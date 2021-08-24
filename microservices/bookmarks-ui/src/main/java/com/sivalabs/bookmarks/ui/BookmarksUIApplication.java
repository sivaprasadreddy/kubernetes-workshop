package com.sivalabs.bookmarks.ui;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@SpringBootApplication
public class BookmarksUIApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmarksUIApplication.class, args);
    }
}

@Controller
class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
