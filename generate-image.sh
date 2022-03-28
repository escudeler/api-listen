#!/bin/bash

mvn clean package $@

docker build -t escudeler/api-listen .
