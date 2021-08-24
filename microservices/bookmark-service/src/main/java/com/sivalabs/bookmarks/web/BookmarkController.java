package com.sivalabs.bookmarks.web;

import com.sivalabs.bookmarks.domain.Bookmark;
import com.sivalabs.bookmarks.domain.BookmarkRepository;
import com.sivalabs.bookmarks.domain.BookmarkVotes;
import com.sivalabs.bookmarks.domain.VoteServiceClient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
@Slf4j
public class BookmarkController {

    private final BookmarkRepository bookmarkRepository;
    private final VoteServiceClient voteServiceClient;

    @GetMapping
    public List<BookmarkDTO> getAllBookmarks() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll(Sort.by(Direction.DESC, "createdAt"));
        List<Long> ids = bookmarks.stream().map(Bookmark::getId).collect(Collectors.toList());
        List<BookmarkVotes> votes = voteServiceClient.getVotesByBookmarks(ids);
        Map<Long, BookmarkVotes> voteCountMap =
                votes.stream().collect(Collectors.toMap(BookmarkVotes::getBookmarkId, v -> v));
        return bookmarks.stream()
                .map(b -> BookmarkDTO.toBookmarkDTO(b, voteCountMap.get(b.getId())))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bookmark createBookmark(@RequestBody @Validated Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }
}
