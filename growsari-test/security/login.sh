#!/usr/bin/env bash

. ../init.sh
curl -H "Content-Type: application/json" -X GET -d "{}" -u $HOST_USERNAME:$HOST_PASSWORD http://$HOST_ADDRESS:$HOST_PORT/growsari-application/rest/user/login