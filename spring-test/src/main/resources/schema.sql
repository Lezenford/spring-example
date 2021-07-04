create table if not exists users
(
    id   int identity primary key auto_increment,
    name nvarchar2 not null
);