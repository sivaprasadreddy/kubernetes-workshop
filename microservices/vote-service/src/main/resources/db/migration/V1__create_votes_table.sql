create sequence vote_id_seq start with 1 increment by 10;

create table votes (
    id bigint DEFAULT nextval('vote_id_seq') not null,
    bookmark_id bigint not null,
    up_votes int not null,
    down_votes int not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id),
    CONSTRAINT bookmark_id_unique UNIQUE(bookmark_id)
);
