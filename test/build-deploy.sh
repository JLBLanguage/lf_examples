#!/bin/bash

cd /home/honza/Work/LIFERAY/workspace/test/modules/test-service-builder/test-service-builder-service
blade deploy
sudo docker cp /home/honza/Work/LIFERAY/workspace/test/modules/test-service-builder/test-service-builder-service/build/libs/test.service.builder.service-2.0.0.jar ubuntu-liferay:/opt/liferay/deploy

cd /home/honza/Work/LIFERAY/workspace/test/modules/test-service-builder/test-service-builder-api
blade deploy
sudo docker cp /home/honza/Work/LIFERAY/workspace/test/modules/test-service-builder/test-service-builder-api/build/libs/test.service.builder.api-2.0.0.jar ubuntu-liferay:/opt/liferay/deploy

cd /home/honza/Work/LIFERAY/workspace/test/modules/guest-book
blade deploy
sudo docker cp /home/honza/Work/LIFERAY/workspace/test/modules/guest-book/build/libs/guest.book-3.0.0.jar  ubuntu-liferay:/opt/liferay/deploy

