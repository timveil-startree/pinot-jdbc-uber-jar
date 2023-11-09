#!/bin/bash

docker compose up --no-start

docker compose start zookeeper

sleep 15

docker compose start pinot-controller

sleep 15

docker compose start pinot-broker

sleep 15

docker compose start pinot-server