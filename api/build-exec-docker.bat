@echo off

echo mvn clean install
call mvn clean install

echo copy deploy
copy .\target\api.war .\docker\wildfly\api.war

echo docker-compose
call docker-compose -f .\docker\docker-compose.yml build
call docker-compose -f .\docker\docker-compose.yml up