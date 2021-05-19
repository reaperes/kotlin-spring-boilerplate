#!/usr/bin/env bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

. $SCRIPT_DIR/common_libs.sh

CLUSTER_NAME=$(get_cluster_name)

kind delete cluster --name $CLUSTER_NAME
