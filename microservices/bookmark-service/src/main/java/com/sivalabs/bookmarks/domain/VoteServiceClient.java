package com.sivalabs.bookmarks.domain;

import com.sivalabs.bookmarks.config.ApplicationProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class VoteServiceClient {
    private final RestTemplate restTemplate;
    private final ApplicationProperties properties;

    public List<BookmarkVotes> getVotesByBookmarks(List<Long> bookmarkIds) {
        List<BookmarkVotes> votes = new ArrayList<>();
        try {
            String ids = bookmarkIds.stream().map(String::valueOf).collect(Collectors.joining(","));
            String url = properties.getVoteServiceUrl() + "/api/v1/votes?bookmarkIds=" + ids;
            ResponseEntity<BookmarkVotes[]> response =
                    restTemplate.getForEntity(url, BookmarkVotes[].class, ids);
            if (response.getStatusCode() == HttpStatus.OK) {
                votes = Arrays.asList(Objects.requireNonNull(response.getBody()));
                log.info("Votes for bookmark id: {} is : {}", ids, votes);
            }
        } catch (Exception e) {
            log.error("Error while getting votes count for bookmark ids: " + bookmarkIds, e);
        }
        return votes;
    }
}
