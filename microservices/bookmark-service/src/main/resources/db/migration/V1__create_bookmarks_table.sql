create sequence bookmark_id_seq start with 1 increment by 10;

create table bookmarks (
    id bigint DEFAULT nextval('bookmark_id_seq') not null,
    title varchar(500) not null,
    url varchar(1000) not null,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);
