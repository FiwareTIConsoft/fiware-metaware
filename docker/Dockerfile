# Starting image
FROM tomcat:7

MAINTAINER marcoterrinoni <marco.terrinoni@consoft.it>

# Initial update
RUN apt-get -yq update

# Retrieve the war package
ADD https://github.com/FiwareTIConsoft/fiware-metaware/releases/download/v1.0/metaware.war /usr/local/tomcat/webapps/

# Expose Tomcat main port
EXPOSE 8080

CMD ["catalina.sh", "run"]
