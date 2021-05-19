#!/usr/bin/env bash

function get_api_version() {
  yq e '.info.version' api/src/main/resources/application.yaml
}

args="${@:2}"

case $1 in
  get_api_version)
    get_api_version $args
    ;;

  *)
    echo "Valid command is 'get_api_version'. Your command is: '$1'"
    ;;
esac
