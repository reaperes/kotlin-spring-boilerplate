local-init:
	docker-compose -f hack/docker-compose.yml up -d

local-clean:
	docker-compose -f hack/docker-compose.yml down
