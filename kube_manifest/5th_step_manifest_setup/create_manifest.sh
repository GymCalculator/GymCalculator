kubectl create namespace mynamespace

kubectl apply -f ../1st_step_deployment/configmap.yaml
kubectl apply -f ../1st_step_deployment/secret.yaml
kubectl apply -f ../1st_step_deployment/deployment.yaml

kubectl apply -f ../2nd_step_service/service.yaml

kubectl apply -f ../3rd_step_ingress/ingress.yaml

kubectl apply -f ../4th_step_database/db-secret.yaml
kubectl apply -f ../4th_step_database/statefulset.yaml
