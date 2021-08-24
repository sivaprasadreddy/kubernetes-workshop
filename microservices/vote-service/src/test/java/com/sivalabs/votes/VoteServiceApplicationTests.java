package com.sivalabs.votes;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sivalabs.votes.domain.Vote;
import com.sivalabs.votes.domain.VoteRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {PostgreSQLContainerInitializer.class})
class VoteServiceApplicationTests {

    @Autowired private VoteRepository voteRepository;
    @Autowired private MockMvc mockMvc;

    private List<Vote> voteList;

    @BeforeEach
    void setUp() {
        voteRepository.deleteAll();

        Vote vote1 = new Vote(null, 1L, 1, 0, LocalDateTime.now(), null);
        Vote vote2 = new Vote(null, 2L, 2, 1, LocalDateTime.now(), null);

        voteList = List.of(vote1, vote2);
        voteRepository.saveAll(voteList);
    }

    @Test
    void shouldFetchVotesForBookmarks() throws Exception {
        this.mockMvc
                .perform(get("/api/v1/votes?bookmarkIds=1,2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(voteList.size())));
    }

    @Test
    void shouldUpVoteBookmark() throws Exception {
        this.mockMvc
                .perform(put("/api/v1/bookmarks/1/votes/up").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookmarkId", is(1)))
                .andExpect(jsonPath("$.upVotes", is(2)))
                .andExpect(jsonPath("$.downVotes", is(0)));
    }

    @Test
    void shouldDownVoteBookmark() throws Exception {
        this.mockMvc
                .perform(put("/api/v1/bookmarks/1/votes/down").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookmarkId", is(1)))
                .andExpect(jsonPath("$.upVotes", is(1)))
                .andExpect(jsonPath("$.downVotes", is(1)));
    }
}
