--liquibase formatted sql
--changeset dkluczewski:1
CREATE TABLE POST (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(400) NOT NULL,
                      content varchar(2000) NULL,
                      created timestamp
);

