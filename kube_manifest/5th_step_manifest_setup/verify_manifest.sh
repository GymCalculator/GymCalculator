kubectl get all -n GymCalculatorNamespace

kubectl describe ingress my-spring-boot-app-ingress -n GymCalculatorNamespace

curl -k -X GET yourdomain.com # domain specified in the ingress manifest