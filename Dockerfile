# The first line is always FROM - this defines a base image: I need tomcat and java 8 to run this program
FROM tomcat:8.0-jre-8

LABEL maintainer="Antonio Ragusa"

# let's imagine that the WAR file already exists...
# we want to pass the WAR file to tomcat's webapps directory
ADD target/FrontController.war /usr/local/tomcat/webapps

# The EXPOSE command informs Docker that the container listens on the
# specified port at runtime
EXPOSE 8080

# The CMD instruction specifies what to run when the container is run
# In our case, the tomcat server is started by running this shell script
CMD ["catalina.sh", "run"]
