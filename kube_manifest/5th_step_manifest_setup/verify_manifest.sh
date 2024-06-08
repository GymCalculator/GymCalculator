kubectl get all -n mynamespace

kubectl describe ingress my-spring-boot-app-ingress -n mynamespace

curl -k -X GET yourdomain.com # domain specified in the ingress manifest