CREATE TABLE IF NOT EXISTS profiles(
    id bigint not null auto_increment,
    profile_id varchar(300) not null unique,
    name varchar(300) not null unique,
    active tinyint not null,

    primary key(id)
);