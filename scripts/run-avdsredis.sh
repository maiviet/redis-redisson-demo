#!/usr/bin/env bash

# This script stops, removes and starts a the avdsredis container.

[[ -z "${AV_ROOT}" ]] && echo "AV_ROOT must be set to the root directory into which all AV repositories are cloned." && exit 1
. $AV_ROOT/av-scripts/be/containers/set-env.sh

export CONTAINER_NAME=avdsredis
export IMAGE_NAME=redis:5.0.8

# stop avdsredis
docker stop avdsredis
# remove container
docker container rm avdsredis 

# Start container

docker run -d -p 6379:6379 --name $CONTAINER_NAME $IMAGE_NAME

#docker run -d -p 6379:6379 --name avdsredis redis:5.0.8 --requirepass phunguyen
