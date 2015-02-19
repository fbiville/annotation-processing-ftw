#!/bin/bash

container_id=$1
maven_arguments="${@:2}"

error_msg="Try again. $O CONTAINER_ID MAVEN_ARGS\nExample: $0 1ae47ef1d clean install"

if [ -z $container_id ]; then
	>&2 echo "Missing CONTAINER_ID. Run docker ps to find it"
fi

if [ -z "$maven_arguments" ]; then
	>&2 echo "Missing MAVEN_ARGS. Valid example: clean install, clean package..."
fi

docker exec -it $container_id mvn $maven_arguments -f /volume/devoxx_fr


