INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO users(name, username, email, password) VALUES('test','test', 'test@test.com', '$2a$10$WlW9TJuHNxH9t4BhrP.zdOLOMrKkJVZNwuoBtqp8mGWuFbsz.7gmm');

INSERT INTO user_roles (user_id, role_id) values (select id from users where username = 'test', select id from roles where name = 'ROLE_USER' )
