#!/usr/bin/env bash

. ../init.sh
curl -H "Content-Type: application/json" -X PATCH -d @updateTopic.json -u $HOST_USERNAME:$HOST_PASSWORD http://$HOST_ADDRESS:$HOST_PORT/growsari-application/rest/topic/cb7d2139-de52-485c-af55-3c2c837b5d6a