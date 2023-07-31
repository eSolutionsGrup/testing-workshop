#!/bin/sh
echo Clean jMeter past results directory for project
rm -r "$HOME"/jmeter_results/testing-workshop/

# if any of the following commands fail, exit script
set -e

echo Building project docker image
docker build -t testing-workshop:latest .
echo Finished building project docker image

echo Starting docker-compose-test.yml which will run jMeter test plan inside a docker container
docker compose -f docker-compose-test.yml up --remove-orphans --abort-on-container-exit --force-recreate
echo All done! Check jMeter test result report here file:///"$HOME"/jmeter_results/testing-workshop/index.html
