FROM ubuntu:14.04

MAINTAINER _aayek <aayekk@gmail.com>

ENV REFRESHED_AT 2015-09-21

RUN apt-get -yqq update
RUN apt-get -yqq install tomcat7 default-jdk

ENV CATALINA_HOME /usr/share/tomcat7
ENV CATALINA_BASE /var/lib/tomcat7
ENV CATALINA_PID /var/run/tomcat7.pid
ENV CATALINA_SH /usr/share/tomcat7/bin/catalina.sh
ENV CATALINA_TMPDIR /tmp/tomcat7-tomcat7-tmp

RUN mkdir -p $CATALINA_TMPDIR

CMD wget "https://github.com/FiwareTIConsoft/fiware-metaware/blob/master/dist/metaware.war?raw=true" -P "/var/lib/tomcat7/webapps/" -O metaware.war

EXPOSE 8080

ENTRYPOINT [ "/usr/share/tomcat7/bin/catalina.sh", "run" ]
