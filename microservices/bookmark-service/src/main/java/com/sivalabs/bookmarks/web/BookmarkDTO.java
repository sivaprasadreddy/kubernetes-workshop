package com.sivalabs.bookmarks.web;

import com.sivalabs.bookmarks.domain.Bookmark;
import com.sivalabs.bookmarks.domain.BookmarkVotes;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookmarkDTO {
    private Long id;
    private String title;
    private String url;
    private Integer upVotes = 0;
    private Integer downVotes = 0;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BookmarkDTO toBookmarkDTO(Bookmark bookmark, BookmarkVotes votes) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setTitle(bookmark.getTitle());
        dto.setUrl(bookmark.getUrl());
        dto.setCreatedAt(bookmark.getCreatedAt());
        dto.setUpdatedAt(bookmark.getUpdatedAt());
        if (votes != null) {
            dto.setUpVotes(votes.getUpVotes());
            dto.setDownVotes(votes.getDownVotes());
        }
        return dto;
    }
}
