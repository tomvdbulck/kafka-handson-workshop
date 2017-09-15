#!/bin/sh


echo "starting Kafka"

# Run Zookeeper single node
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties