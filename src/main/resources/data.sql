INSERT INTO role (id, name)
VALUES (1, 'Admin');
INSERT INTO role (id, name)
VALUES (2, 'Mod');
INSERT INTO role (id, name)
VALUES (3, 'Member');

INSERT INTO USER (id, username, email, password, role_id)
VALUES (1, 'admin', 'admin@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 1);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (2, 'member1', 'member1@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 3);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (3, 'member2', 'member2@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 3);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (4, 'member3', 'member3@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 3);
INSERT INTO USER (id, username, email, password, role_id)
VALUES (5, 'mod1', 'mod1@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 2);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (6, 'mod2', 'mod2@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 2);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (7, 'mod3', 'mod3@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 2);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (8, 'member4', 'member4@gmail.com', '$2a$10$Gh.3UWeLfCyNRfXeRwFfX.QfObU9skGgLWqkU6uZZEW4u6MdH1Nra', 3);

insert into tag (name)
values ('Sport');
insert into tag (name)
values ('Music');
insert into tag (name)
values ('eSport');

