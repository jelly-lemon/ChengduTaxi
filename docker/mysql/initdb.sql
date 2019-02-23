CREATE DATABASE company character set utf8;

USE company;


CREATE TABLE employees (
first_name varchar(25),
last_name  varchar(25),
department varchar(15),
email  varchar(50)
);


INSERT INTO employees (first_name, last_name, department, email) VALUES ('Lorenz', 'Vanthillo', 'IT', 'lvthillo@mail.com');
INSERT INTO employees (first_name, last_name, department, email) VALUES ('张', '三', '测试部门', 'zhangsan@mail.com');
INSERT INTO employees (first_name, last_name, department, email) VALUES ('林', '二', '测试部门', 'zhangsan@mail.com');


use mysql;
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'mysql';
FLUSH PRIVILEGES;

