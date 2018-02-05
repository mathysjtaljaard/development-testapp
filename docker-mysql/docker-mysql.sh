#!/bin/bash

sudo docker stop mysql

echo "y" | sudo docker container prune
echo "y" | sudo docker image prune

echo $path

sudo docker run --name mysql --restart on-failure:3 \
-v ~/containers/mysql:/var/lib/mysql \
-v $(pwd)/scripts:/docker-entrypoint-initdb.d \
-p 3306:3306 \
-e MYSQL_ALLOW_EMPTY_PASSWORD=no \
-e MYSQL_ROOT_PASSWORD=root12345 \
-e MYSQL_USER=app \
-e MYSQL_PASSWORD=app12345 \
-d \
mysql:latest