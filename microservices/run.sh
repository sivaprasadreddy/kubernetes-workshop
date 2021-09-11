#!/bin/bash

function buildImages() {
    mvn clean package jib:build -pl vote-service
    mvn clean package jib:build -pl bookmark-service
    mvn clean package jib:build -pl bookmarks-ui
}

function k8sDeploy() {
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
}

function k8sUndeploy() {
    kubectl delete -f k8s/6-bookmarks-ui-app.yaml
    kubectl delete -f k8s/5-vote-service-app.yaml
    kubectl delete -f k8s/4-bookmark-service-app.yaml
    kubectl delete -f k8s/3-votes-postgresdb.yaml
    kubectl delete -f k8s/2-bookmarks-postgresdb.yaml
    kubectl delete -f k8s/1-config.yaml
}

"$@"
