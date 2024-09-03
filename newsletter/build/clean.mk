# Include common variables
include build/common.mk

# Clean up local Docker images
.PHONY: clean
clean: $(SERVICES:%=clean-%)

$(SERVICES:%=clean-%):
	-@docker rmi $(REGISTRY)/$(@:clean-%=%):$(TAG) || true