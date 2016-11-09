#! /bin/bash

# origin incidents
kafka-topics.sh --zookeeper localhost:2181 --create --topic origin_incidents --partitions 24 --replication 1

# processed incidents
/kafka-topics.sh --zookeeper localhost:2181 --create --topic processed_incidents --partitions 24 --replication 1