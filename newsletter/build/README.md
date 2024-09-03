# Usage

- `make build && make push && make clean` - last one is optional.

## Prerequisites

- `docker buildx create --use --name mybuilder`
- `docker buildx inspect mybuilder --bootstrap`
