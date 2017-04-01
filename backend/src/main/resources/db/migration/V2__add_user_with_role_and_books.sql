INSERT INTO person VALUES
  (1001, 'Mateusz', 'Sioja', 'admin', '$2a$10$0kzdNMnHDYU1GQ3geJEGSec4hvrrVdkgogiJOsJwICJQqJSswTkK6' );

INSERT INTO role VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER');

INSERT INTO book VALUES
  (1001, '2154886', 'J.K. Rowling', 'Harry Potter', 'Fantasy', 1001),
  (1002, '2124886', 'Coben Harlan', 'Hole', 'Horror', 1001);

INSERT INTO user_role VALUES
  (1001, 1);