.PHONY: apply-dapr
apply-dapr:
	kubectl apply -R -f dapr

.PHONY: apply-services
apply-services:
	kubectl apply -R -f services

.PHONY: apply-other
apply-other:
	kubectl apply -R -f other

# Apply everything
.PHONY: apply-all
apply-all: apply-other apply-dapr apply-services