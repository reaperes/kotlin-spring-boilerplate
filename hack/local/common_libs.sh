SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

function get_cluster_name() {
  sed -rn 's/^name:[[:space:]]*(.*)/\1/gp' $SCRIPT_DIR/kind.yaml
}

function get_docker_images() {
  sed -rn 's/^name:[[:space:]]*(.*)/\1/gp' $SCRIPT_DIR/kind.yaml
}
