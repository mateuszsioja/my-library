DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS user_role;

CREATE TABLE person
(
  user_id   SERIAL NOT NULL,
  name      TEXT NOT NULL,
  surname   TEXT NOT NULL,
  login     TEXT NOT NULL UNIQUE,
  password  TEXT NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE role
(
  role_id SERIAL NOT NULL,
  role    TEXT   NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE book
(
  book_id       SERIAL NOT NULL,
  serial_number TEXT   NOT NULL,
  author        TEXT   NOT NULL,
  title         TEXT   NOT NULL,
  category      TEXT   NOT NULL,
  user_id       INTEGER,
  PRIMARY KEY (book_id),
  FOREIGN KEY (user_id) REFERENCES person(user_id)
);

CREATE TABLE user_role
(
  user_id   INTEGER REFERENCES person(user_id),
  role_id   INTEGER REFERENCES role(role_id),
  CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id)
);