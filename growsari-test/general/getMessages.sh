#!/usr/bin/env bash

. ../init.sh
curl -H "Content-Type: application/json" -X GET -d @getMessages.json -u $HOST_USERNAME:$HOST_PASSWORD http://$HOST_ADDRESS:$HOST_PORT/growsari-application/rest/topic/4592a10e-da14-4a86-b337-d1a6e4f1b02d/messages