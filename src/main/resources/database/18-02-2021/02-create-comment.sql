--liquibase formatted sql
--changeset dkluczewski:1
CREATE TABLE COMMENT (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         post_id BIGINT NOT NULL,
                         content varchar(2000) NULL,
                         created timestamp
);

ALTER TABLE COMMENT
    ADD CONSTRAINT comment_post_id
    FOREIGN KEY (post_id) REFERENCES post(id)