package com.sivalabs.votes.web;

import com.sivalabs.votes.domain.Vote;
import com.sivalabs.votes.domain.VoteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class VoteController {

    private final VoteRepository voteRepository;

    @GetMapping("/votes")
    public ResponseEntity<List<Vote>> getVotes(
            @RequestParam("bookmarkIds") List<Long> bookmarkIds) {
        List<Vote> votes = voteRepository.findByBookmarkIds(bookmarkIds);
        return ResponseEntity.ok(votes);
    }

    @PutMapping("/bookmarks/{bookmarkId}/votes/up")
    public ResponseEntity<Vote> upVote(@PathVariable Long bookmarkId) {
        Vote vote = voteRepository.findByBookmarkId(bookmarkId).orElse(new Vote());
        vote.setBookmarkId(bookmarkId);
        vote.setUpVotes(vote.getUpVotes() + 1);
        voteRepository.save(vote);
        return ResponseEntity.ok(vote);
    }

    @PutMapping("/bookmarks/{bookmarkId}/votes/down")
    public ResponseEntity<Vote> downVote(@PathVariable Long bookmarkId) {
        Vote vote = voteRepository.findByBookmarkId(bookmarkId).orElse(new Vote());
        vote.setBookmarkId(bookmarkId);
        vote.setDownVotes(vote.getDownVotes() + 1);
        voteRepository.save(vote);
        return ResponseEntity.ok(vote);
    }
}
