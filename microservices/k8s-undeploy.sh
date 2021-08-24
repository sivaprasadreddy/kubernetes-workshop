#!/bin/bash

kubectl delete -f k8s/6-bookmarks-ui-app.yaml
sleep 5
kubectl delete -f k8s/5-vote-service-app.yaml
sleep 5
kubectl delete -f k8s/4-bookmark-service-app.yaml
sleep 5
kubectl delete -f k8s/3-votes-postgresdb.yaml
sleep 5
kubectl delete -f k8s/2-bookmarks-postgresdb.yaml
sleep 5
kubectl delete -f k8s/1-config.yaml
