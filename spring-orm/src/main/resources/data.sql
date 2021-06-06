insert into USERS (NAME)
select 'TEST'
where not exists(select * from USERS where NAME = 'TEST');

insert into ROLES (NAME)
select 'ADMIN'
where not exists(select * from ROLES where NAME = 'ADMIN')
union
select 'USER'
where not exists(select * from ROLES where NAME = 'USER');

insert into USER_ROLE (USERID, ROLEID)
select (select ID from USERS where NAME = 'TEST'), (select ID from ROLES where NAME = 'ADMIN')
where not exists(select *
                 from USER_ROLE
                 where USERID = (select ID from USERS where NAME = 'TEST')
                   and ROLEID = (select ID from ROLES where NAME = 'ADMIN'))
union
select (select ID from USERS where NAME = 'TEST'), (select ID from ROLES where NAME = 'USER')
where not exists(select *
                 from USER_ROLE
                 where USERID = (select ID from USERS where NAME = 'TEST')
                   and ROLEID = (select ID from ROLES where NAME = 'USER'));

insert into COMMENTS (TEXT, USERID)
VALUES ('New commit', select ID from USERS where NAME = 'TEST');