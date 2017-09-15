#!/bin/sh


echo "starting Zookeeper"

# Run Zookeeper single node
$KAFKA_HOME/bin/zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties