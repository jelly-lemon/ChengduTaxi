#基础镜像
FROM mysql:8.0.13

#作者
MAINTAINER lmTest <lmTest@gmail.com>

#设置 root 用户密码
ENV MYSQL_ROOT_PASSWORD mysql

#移除 MySQL 原本配置
RUN		rm	/etc/mysql/conf.d/mysql.cnf

#复制新 MySQL 配置
COPY 	./mysql.cnf		/etc/mysql/conf.d/

#复制初始化 sql 文件
#COPY 	./initdb.sql	/docker-entrypoint-initdb.d/





#创建一个数据库
#ENV MYSQL_DATABASE company
