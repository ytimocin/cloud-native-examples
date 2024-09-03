# Include common variables
include build/common.mk

# Define the platforms you want to support
PLATFORMS = linux/amd64,linux/arm64

# Build all services
.PHONY: build
build: $(SERVICES:%=build-%)

$(SERVICES:%=build-%):
	echo "Building service: $(@:build-%=%)"
	cd $(@:build-%=%) && docker buildx build --platform $(PLATFORMS) -t $(REGISTRY)/$(@:build-%=%):$(TAG) .