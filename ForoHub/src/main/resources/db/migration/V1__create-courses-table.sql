CREATE TABLE IF NOT EXISTS courses(
    id bigint not null auto_increment,
    course_id varchar(300) not null unique,
    name varchar(300) not null unique,
    category varchar(100) not null,
    active tinyint not null,

    primary key(id)
    );