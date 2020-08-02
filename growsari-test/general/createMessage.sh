#!/usr/bin/env bash

. ../init.sh
curl -H "Content-Type: application/json" -X POST -H "Accept-Language: pl-PL" -d @createMessage.json -u $HOST_USERNAME:$HOST_PASSWORD http://$HOST_ADDRESS:$HOST_PORT/growsari-application/rest/topic/ca5c48b1-6492-4c33-90fb-e1fee25511a2/message