#!/bin/bash -ex
java -jar -Dspring.profiles.active="${ENVIRONMENT}" -Duser.timezone=UTC -Duser.language=en -Duser.region=US be-api-gateway-0.0.1-SNAPSHOT.jar