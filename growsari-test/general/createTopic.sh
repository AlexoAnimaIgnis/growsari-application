#!/usr/bin/env bash

. ../init.sh
curl -H "Content-Type: application/json" -X POST -H "Accept-Language: pl-PL" -d @createTopic.json -u $HOST_USERNAME:$HOST_PASSWORD http://$HOST_ADDRESS:$HOST_PORT/growsari-application/rest/topic