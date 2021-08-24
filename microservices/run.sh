#!/bin/bash

mvn jib:build -pl vote-service
mvn jib:build -pl bookmark-service
mvn jib:build -pl bookmarks-ui

kubectl apply -f 1-config.yaml
kubectl apply -f 2-bookmarks-postgresdb.yaml
sleep 5
kubectl apply -f 3-votes-postgresdb.yaml
sleep 5
kubectl apply -f 4-bookmark-service-app.yaml
sleep 5
kubectl apply -f 5-vote-service-app.yaml
sleep 5
kubectl apply -f 6-bookmarks-ui-app.yaml
