create table if not exists users
(
    id   int primary key auto_increment,
    name varchar2 not null unique
);

create table if not exists roles
(
    id   int primary key auto_increment,
    name varchar2 not null unique
);

create table if not exists user_role
(
    userId int not null,
    roleId int not null,
    foreign key (userId) references users (id),
    foreign key (roleId) references roles (id)
);

create table if not exists comments
(
    id     int primary key auto_increment,
    text   varchar2 not null,
    userId int      not null,
    foreign key (userId) references users (id)
);

