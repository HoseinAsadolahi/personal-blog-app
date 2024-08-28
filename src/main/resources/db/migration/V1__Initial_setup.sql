CREATE TABLE IF NOT EXISTS users
(
    email      VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(30)  NOT NULL
    );

CREATE TABLE IF NOT EXISTS articles
(
    id             BIGINT PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    content        TEXT         NOT NULL,
    published_date DATE         NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS articles_seq AS BIGINT OWNED BY articles.id;

CREATE TABLE IF NOT EXISTS comments
(
    id         BIGINT PRIMARY KEY,
    text       TEXT NOT NULL,
    user_email VARCHAR(255),
    article_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_email) REFERENCES users (email) ON DELETE CASCADE,
    CONSTRAINT fk_article_comment
        FOREIGN KEY (article_id) REFERENCES articles (id) ON DELETE CASCADE
);

CREATE SEQUENCE IF NOT EXISTS comments_seq AS BIGINT OWNED BY comments.id;

CREATE TABLE IF NOT EXISTS article_likes
(
    article_id BIGINT,
    user_email VARCHAR(255),
    PRIMARY KEY (article_id, user_email),
    FOREIGN KEY (article_id) REFERENCES articles (id) ON DELETE CASCADE,
    FOREIGN KEY (user_email) REFERENCES users (email) ON DELETE CASCADE
);