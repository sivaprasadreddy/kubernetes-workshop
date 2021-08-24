package com.sivalabs.votes.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_id_generator")
    @SequenceGenerator(
            name = "vote_id_generator",
            sequenceName = "vote_id_seq",
            allocationSize = 10)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "BookmarkId cannot be empty")
    private Long bookmarkId;

    @Column private Integer upVotes = 0;

    @Column private Integer downVotes = 0;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
