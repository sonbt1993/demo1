INSERT INTO role (id, name)
VALUES (1, 'Admin');

INSERT INTO role (id, name)
VALUES (2, 'Member');

INSERT INTO USER (id, username, email, password, role_id)
VALUES (1, 'admin', 'admin@gmail.com', 123, 1);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (2, 'member1', 'member1@gmail.com', 123, 2);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (3, 'member2', 'member2@gmail.com', 123, 2);
INSERT INTO USER  (id, username, email, password, role_id)
VALUES (4, 'member3', 'member3@gmail.com', 123, 2);

insert into tag (name)
values ('Sport');
insert into tag (name)
values ('Music');
insert into tag (name)
values ('eSport');

