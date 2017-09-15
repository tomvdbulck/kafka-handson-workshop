FROM java:openjdk-8-jre

MAINTAINER Tom Van den Bulck (tomvdbulck@gmail.com)

# Based on https://github.com/wurstmeister/kafka-docker

ENV DEBIAN_FRONTEND=noninteractive

ENV KAFKA_VERSION=0.11.0.1
ENV SCALA_VERSION=2.11
ENV KAFKA_HOME /opt/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION"


# Install Kafka, Zookeeper and other needed things
RUN apt-get update && \
    apt-get install -y wget supervisor dnsutils && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean && \
    wget -q http://www-eu.apache.org/dist/kafka/"$KAFKA_VERSION"/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz -O /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz && \
    tar xfz /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz -C /opt && \
    rm /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz


ADD scripts/start-kafka.sh /usr/bin/start-kafka.sh
ADD scripts/broker-list.sh /usr/bin/broker-list.sh

RUN chmod a+x /usr/bin/start-kafka.sh && \
    chmod a+x /usr/bin/broker-list.sh

# Supervisor config
ADD supervisor/kafka.conf /etc/supervisor/conf.d/


VOLUME ["/kafka"]
ENV PATH ${PATH}:${KAFKA_HOME}/bin


CMD ["supervisord", "-n"]


