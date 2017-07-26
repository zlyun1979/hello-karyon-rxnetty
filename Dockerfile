FROM openjdk:alpine
VOLUME /tmp
RUN cd /root; mkdir -p .webgoat
ADD build/libs/hello-karyon-rxnetty-all-0.1.0.jar h-k-rx-all.jar
RUN sh -c 'touch /h-k-rx-all.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/h-k-rx-all.jar"]