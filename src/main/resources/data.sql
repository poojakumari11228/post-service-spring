-- USERS

INSERT INTO users (id, name, email, password)
VALUES (111, 'Pooja Kumari', 'poojakumari@abc.com', '$2a$12$KQ1lFVGXn0NkbssSUUbnD.kfB3TFDk.H2mtjrxnHYw2RwcM9s0O2S');

INSERT INTO users (id, name, email, password)
VALUES (112, 'John', 'john@abc.com', '$2a$12$JlpcNcTxngiFp4sZCitvX.tPlkHVko9/D3cIoPZSH/1164KKYQRhO');

INSERT INTO role (id, type)
VALUES (1, 'ADMIN');
INSERT INTO role (id, type)
VALUES (2, 'USER');

INSERT INTO users_roles (user_id, roles_id)
VALUES (111, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (111, 2);
INSERT INTO users_roles (user_id, roles_id)
VALUES (112, 2);

INSERT INTO post (id, author, content, title, user_id)
VALUES  (100, 'Pooja', 'Java is an Object Oriented Language', 'OOP', 111 );




INSERT INTO post (id, author, content, title, user_id)
VALUES  (101, 'Pooja', '2nd post', '2', 111 );

INSERT INTO post (id, title, content, author, user_id)
VALUES (103, 'Hello World', 'This is my 3rd post', 'Pooja', 111);


INSERT INTO comment(id, name, text, post_id)
VALUES (101, 'Pooja', 'Nice Work!', 100 )

