--liquibase formatted sql

--changeset liquibase:1
CREATE TABLE if not exists users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR,
    password VARCHAR,
    email VARCHAR,
    roles VARCHAR,
    dateAt timestamp,
    lastLogin timestamp
);

--changeset liquibase:2c
INSERT INTO users
(id,
 username,
 password,
 email,
 dateAt,
 lastLogin
)
VALUES(
          DEFAULT,
          'admin',
          '123',
          'admin@email.com',
          localtimestamp,
          localtimestamp
      )