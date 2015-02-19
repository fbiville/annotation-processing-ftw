FROM ubuntu:14.10

MAINTAINER Florent Biville <florent.biville@gmail.com>
MAINTAINER Sébastien Lesaint <sebastien.lesaint@gmail.com>

RUN apt-get update && apt-get install -y wget git

RUN cd /home \
	&& wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" -O- http://download.oracle.com/otn-pub/java/jdk/8u31-b13/jdk-8u31-linux-x64.tar.gz | tar xz \
	&& ln -s jdk1.8.0_31 jdk8 

RUN cd /home \
	&& wget --no-check-certificate --no-cookies -O- http://apache.websitebeheerjd.nl/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz | tar xz \
	&& ln -s apache-maven-3.2.5 maven 

ENV MVN_HOME /home/maven
ENV JAVA_HOME /home/jdk8
ENV PATH $JAVA_HOME/bin:$MVN_HOME/bin:$PATH

CMD ["/bin/bash"]
