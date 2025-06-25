insert into customers_data (name, email, password, created, modified, last_login, token, is_active)
values ('Mauricio', 'fail-email@yahoo.com', '12345', NOW(), NOW(), NOW(), 'oiaj039r902kjsahfhowwieq9ie882029j', true),
       ('Luis', 'holacomo@hotmail.com', '12345', NOW(), NOW(), NOW(), 'ooqwieopqwke8h37dwr902kjsahfhowwieq9ie882029j', true),
       ('Gabriel', 'gabi-gol@outlo.com', '12345', NOW(), NOW(), NOW(), 'lkjwpeokqwpeom1029e2190ir2¿33rjif', true);

insert into customers_phones (number, city_code, country_code, customer_id)
values ('97763533', '45', '56', 1),
       ('98876543', '34', '56', 1),
       ('62235212', '22', '56', 2),
       ('98876543', '9', '56', 3),
       ('62235212', '22', '56', 3);