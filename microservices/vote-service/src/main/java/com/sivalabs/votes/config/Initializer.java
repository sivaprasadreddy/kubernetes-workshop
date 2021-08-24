package com.sivalabs.votes.config;

import com.sivalabs.votes.domain.Vote;
import com.sivalabs.votes.domain.VoteRepository;
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

    private final VoteRepository voteRepository;

    @Override
    public void run(String... args) {
        log.info("Running Initializer.....");
        voteRepository.deleteAllInBatch();

        Vote v1 = new Vote(null, 1L, 1, 0, LocalDateTime.now(), null);
        Vote v2 = new Vote(null, 2L, 2, 1, LocalDateTime.now(), null);
        Vote v3 = new Vote(null, 3L, 1, 0, LocalDateTime.now(), null);
        Vote v4 = new Vote(null, 4L, 4, 1, LocalDateTime.now(), null);
        Vote v5 = new Vote(null, 5L, 1, 3, LocalDateTime.now(), null);

        voteRepository.saveAll(List.of(v1, v2, v3, v4, v5));
    }
}
