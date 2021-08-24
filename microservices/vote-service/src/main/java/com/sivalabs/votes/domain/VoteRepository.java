package com.sivalabs.votes.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByBookmarkId(Long bookmarkId);

    @Query("select v from Vote v where v.bookmarkId in :bookmarkIds")
    List<Vote> findByBookmarkIds(List<Long> bookmarkIds);
}
