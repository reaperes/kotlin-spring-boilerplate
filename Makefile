IMG = reaperes/test
TAG := $(shell hack/utils.sh get_api_version)

build-jar:
	./gradlew clean :api:build

build-image:
	docker build ./api -t $(IMG):$(TAG)

release-image: build-image
	docker push $(IMG):$(TAG)

init-local:
	hack/local/init-local.sh

clean-local:
	hack/local/clean-local.sh

e2e-test:
	./gradlew :api-e2e-test:test

.PHONY: build-jar build-image release-image init-local clean-local e2e-test
