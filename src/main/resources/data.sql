INSERT INTO roles ( name, description)  VALUES ( 'ADMIN', 'Manage everyone');
INSERT INTO roles ( name, description)  VALUES ( 'EDITOR', 'Update catalog');
INSERT INTO roles ( name, description)  VALUES ( 'SALES', 'Sell and buy');


INSERT INTO users (email, password, first_name, last_name) VALUES ('wijiejs@gmail.com' , '123test', 'Wijnie', 'Lovejss');
INSERT INTO users (email, password, first_name, last_name) VALUES ('bbbb@gmail.com' , '123test', 'bbbb', 'ddvesss');
INSERT INTO users (email, password, first_name, last_name) VALUES ('wwww@gmail.com' , '123test', 'wwww', 'dess');
INSERT INTO users (email, password, first_name, last_name) VALUES ('awssw@gmail.com' , '123test', 'awaw', 'wess');


INSERT INTO users_roles(user_id, role_id) values (1, 3);
INSERT INTO users_roles(user_id, role_id) values (2, 2);
INSERT INTO users_roles(user_id, role_id) values (3, 1);

