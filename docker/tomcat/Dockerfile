#基础镜像
FROM tomcat:8.5.37

#作者
MAINTAINER lmTest <lmTest@gmail.com>

#删除原文件 tomcat-users.xml
RUN 	rm 	/usr/local/tomcat/conf/tomcat-users.xml

#复制文件 tomcat-users.xml
COPY 	./tomcat-users.xml		/usr/local/tomcat/conf/

#删除原文件 server.xml
RUN		rm	/usr/local/tomcat/conf/server.xml

#复制文件 server.xml
COPY 	./server.xml			/usr/local/tomcat/conf/

#删除原文件 context.xml
RUN 	rm 	/usr/local/tomcat/webapps/manager/META-INF/context.xml

#复制文件 context.xml
COPY 	./context.xml        	/usr/local/tomcat/webapps/manager/META-INF/

#复制 lmArtifacts.war
COPY	./ChengduTaxi.war		/usr/local/tomcat/webapps

#设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone




#复制文件 mysql-connector-java-5.1.46.jar
#COPY	./mysql-connector-java-5.1.46.jar	/usr/local/tomcat/lib