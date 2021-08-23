# Kind Setup

```shell
brew install kind
kind create cluster
kind create cluster --name dev-cluster
kind get clusters
kind load docker-image my-custom-image-0 my-custom-image-1
kind load docker-image my-custom-image-0 my-custom-image-1 --name dev-cluster
kind delete cluster
kind delete cluster --name dev-cluster
```

## Deploying the Dashboard UI

Refer: https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

```shell
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.2.0/aio/deploy/recommended.yaml
kubectl apply -f dashboard-adminuser.yaml
kubectl -n kubernetes-dashboard get secret $(kubectl -n kubernetes-dashboard get sa/admin-user -o jsonpath="{.secrets[0].name}") -o go-template="{{.data.token | base64decode}}"
kubectl proxy
```

Access http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/


```shell
kubectl -n kubernetes-dashboard delete serviceaccount admin-user
kubectl -n kubernetes-dashboard delete clusterrolebinding admin-user
```
