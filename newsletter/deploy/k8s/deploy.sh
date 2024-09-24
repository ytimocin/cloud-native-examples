# Create the necessary namespaces
kubectl apply -R -f namespaces

# Deploy the necessary services
kubectl apply -R -f services
