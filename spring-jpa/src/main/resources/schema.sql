create table roles
(
    id   int identity primary key,
    name nvarchar2 not null
);

create table users
(
    id       int identity primary key,
    name     varchar2 not null,
    lastName varchar2 not null,
    roleId   int      not null,
    foreign key (roleId) references roles (id)
);

create table products
(
    id   int identity primary key,
    name varchar2 not null,
    cost int      not null
);
create table carts
(
    id        int identity primary key,
    userId    int not null,
    productId int not null,
    constraint UserId_ProductId unique (userId, productId),
    constraint FK_UserId foreign key (userId) references USERS (id),
    constraint FK_productId foreign key (productId) references products (id)
);
