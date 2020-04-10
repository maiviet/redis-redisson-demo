#!/usr/bin/env bash

# This script stops, removes and starts a the avdsredis container.

export CONTAINER_NAME=avdsredis
export IMAGE_NAME=redis:5.0.8

# stop avdsredis
docker stop avdsredis
# remove container
docker container rm avdsredis 

# Start container

docker run -d -p 6379:6379 --name $CONTAINER_NAME $IMAGE_NAME

#docker run -d -p 6379:6379 --name avdsredis redis:5.0.8 --requirepass phunguyen
