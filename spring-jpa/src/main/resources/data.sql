insert into roles (name)
values ('USER'),
       ('ADMIN');

insert into users (name, lastName, roleId)
values ('Alex', 'Ivanov', select id from roles where name = 'USER');

insert into products (name, cost)
values ('milk', 10),
       ('juice', 15),
       ('water', 5);

insert into carts (userId, productId)
values ((select id from users where name = 'Alex'), (select id from products where name = 'milk'));