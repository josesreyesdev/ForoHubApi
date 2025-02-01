CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    topic_id VARCHAR(50) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    creation_date DATETIME NOT NULL,
    status VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL,
    author_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,

    CONSTRAINT fk_topics_author FOREIGN KEY (author_id) REFERENCES users(id),
    CONSTRAINT fk_topics_course FOREIGN KEY (course_id) REFERENCES courses(id)
);