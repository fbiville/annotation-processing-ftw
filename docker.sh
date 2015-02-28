#!/bin/bash

PROGRAM="docker.sh"
IMAGE="devoxx_fr_2015/annotation_processing"
case "$1" in
	'build')
		docker build -t "$IMAGE" .
	;;
	'start')
		docker run -i -d -v $(pwd)/exercises/:/volume/devoxx_fr "$IMAGE"
	;;
	'join')
		if [ -z "$2" ]; then
			>&2 echo "Missing CONTAINER_ID. Run $PROGRAM ps to find it"
		else
			docker exec -it "$2" /bin/bash
		fi
	;;
	'ps')
		docker ps -a | less -S
	;;
	'killall')
		read -p "This will stop and delete all your images. Are you sure? " -n 1 -r
		echo
		if [[ $REPLY =~ ^[Yy]$ ]]; then
			targets=$(docker ps -a -q)
			if test -n "$targets"; then
				docker stop $targets && docker rm $targets
			fi
		fi
	;;
	*)
		>&2 echo "Try again. $PROGRAM [build|start|join|ps|killall]"
	;;
esac

