package com.sivalabs.bookmarks.domain;

import lombok.Data;

@Data
public class BookmarkVotes {
    private Long bookmarkId;
    private Integer upVotes;
    private Integer downVotes;
}
