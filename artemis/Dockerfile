FROM openjdk:15-jdk-alpine
LABEL maintainer="Anton Simonov"

#https://github.com/ekim197711/springboot-artemis/blob/master/script/artemis/Dockerfile
RUN mkdir /broker
WORKDIR /broker

RUN apk add libaio wget
RUN wget -O "artemis.tar" "https://www.apache.org/dyn/closer.cgi?filename=activemq/activemq-artemis/2.17.0/apache-artemis-2.17.0-bin.tar.gz&action=download"
RUN tar -xvf ./artemis.tar
RUN /broker/apache-artemis-2.17.0/bin/artemis create --user anton --password simonov --http-host 0.0.0.0 --require-login --relax-jolokia anton

# Web Server
EXPOSE 8161 \
# JMX Exporter
    9404 \
# Port for CORE,MQTT,AMQP,HORNETQ,STOMP,OPENWIRE
    61616 \
# Port for HORNETQ,STOMP
    5445 \
# Port for AMQP
    5672 \
# Port for MQTT
    1883 \
#Port for STOMP
    61613

ENTRYPOINT ["/broker/anton/bin/artemis", "run"]