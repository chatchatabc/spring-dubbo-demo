--liquibase formatted sql

--changeset liquibase:1
CREATE TABLE if not exists users
(
    id SERIAL PRIMARY KEY,
    username VARCHAR,
    password VARCHAR,
    salt VARCHAR,
    email VARCHAR,
    roles VARCHAR,
    dateAt timestamp,
    lastLogin timestamp
);

--changeset liquibase:2
INSERT INTO users
(id,
 username,
 password,
 salt,
 email,
 roles,
 dateAt,
 lastLogin
)
VALUES(
          DEFAULT,
          'admin',
          'd5c398e8b890a14d8c03faa0da1324df9aadd3d3',
          '18D4D05E',
          'admin@example.com',
          'admin',
          localtimestamp,
          localtimestamp
      );
