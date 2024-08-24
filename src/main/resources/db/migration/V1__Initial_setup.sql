-- Migration script: V1__Initial_setup.sql

-- Create the User table if it doesn't exist
DO
$$
BEGIN
    IF
NOT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'users') THEN
CREATE TABLE users
(
    email      VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);
END IF;
END $$;

-- Create the Article table if it doesn't exist
DO
$$
BEGIN
    IF
NOT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'articles') THEN
CREATE TABLE articles
(
    id             BIGINT PRIMARY KEY,
    title          VARCHAR(255) NOT NULL,
    content        TEXT         NOT NULL,
    published_date TIMESTAMP         NOT NULL,
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
END IF;
END $$;

-- Create the Comment table if it doesn't exist
DO
$$
BEGIN
    IF
NOT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'comments') THEN
CREATE TABLE comments
(
    id         BIGINT PRIMARY KEY,
    text       TEXT NOT NULL,
    user_email VARCHAR(255),
    article_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_email) REFERENCES users (email),
    CONSTRAINT fk_article_comment
        FOREIGN KEY (article_id) REFERENCES articles (id) ON DELETE CASCADE
);
END IF;
END $$;

-- Create the Article-Likes relationship table if it doesn't exist
DO
$$
BEGIN
    IF
NOT EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'article_likes') THEN
CREATE TABLE article_likes
(
    article_id BIGINT,
    user_email VARCHAR(255),
    PRIMARY KEY (article_id, user_email),
    FOREIGN KEY (article_id) REFERENCES articles (id) ON DELETE CASCADE,
    FOREIGN KEY (user_email) REFERENCES users (email) ON DELETE CASCADE
);
END IF;
END $$;