CREATE TABLE replies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reply_id CHAR(36) NOT NULL UNIQUE,
    message TEXT,
    creation_date DATETIME NOT NULL,
    solution BOOLEAN,
    active BOOLEAN,
    author_id BIGINT,
    topic_id BIGINT,

    CONSTRAINT fk_replies_author FOREIGN KEY (author_id) REFERENCES users(id),
    CONSTRAINT fk_replies_topic FOREIGN KEY (topic_id) REFERENCES topics(id)
);