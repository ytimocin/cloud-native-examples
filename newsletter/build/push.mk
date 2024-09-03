# Include common variables
include build/common.mk

# Push all services
.PHONY: push
push: $(SERVICES:%=push-%)

$(SERVICES:%=push-%):
	docker push $(REGISTRY)/$(@:push-%=%):$(TAG)