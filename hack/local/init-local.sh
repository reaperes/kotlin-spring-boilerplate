#!/usr/bin/env bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

. $SCRIPT_DIR/common_libs.sh

CLUSTER_NAME=$(get_cluster_name)

printf "\nCheck cluster if exists... "
if ! kind get clusters | grep -q -o "$CLUSTER_NAME"; then
  kind create cluster --config $SCRIPT_DIR/kind.yaml --image kindest/node:v1.20.2
else
  echo ""
  echo "Cluster is already exists. Skip to create cluster."
fi


# Install local-infra
## mysql
printf "\n\n"
echo "Install mysql..."
helm repo add --force-update bitnami https://charts.bitnami.com/bitnami > /dev/null
helm upgrade --install mysql -f /Users/reaperes/IdeaProjects/cpd/woowahan-platform/hack/local/charts/mysql/values.yaml bitnami/mysql --version 8.5.8 > /dev/null

## adminer
echo "Install adminer..."
helm repo add --force-update reaperes https://reaperes.github.io/helm-charts/ > /dev/null
helm upgrade --install adminer -f $SCRIPT_DIR/charts/adminer/values.yaml reaperes/adminer --version 0.1.0 > /dev/null


# boost up pulling images
kind load docker-image --name $CLUSTER_NAME $(kubectl get pods -o=jsonpath='{.items[*].spec.containers[*].image}')


# echo success
echo ""
echo "Success!"
