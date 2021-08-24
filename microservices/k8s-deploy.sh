#!/bin/bash

mvn jib:build -pl vote-service
mvn jib:build -pl bookmark-service
mvn jib:build -pl bookmarks-ui

kubectl apply -f k8s/1-config.yaml
kubectl apply -f k8s/2-bookmarks-postgresdb.yaml
sleep 5
kubectl apply -f k8s/3-votes-postgresdb.yaml
sleep 5
kubectl apply -f k8s/4-bookmark-service-app.yaml
sleep 5
kubectl apply -f k8s/5-vote-service-app.yaml
sleep 5
kubectl apply -f k8s/6-bookmarks-ui-app.yaml
