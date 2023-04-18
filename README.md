# API Listen
Listen - Demo project for gRPC

## Projects list for gRPC example
**Follow the respectives readmes for build and execute them in following list order:**
```sh
git clone https://github.com/escudeler/api-common-proto.git
git clone https://github.com/escudeler/api-listen.git
git clone https://github.com/escudeler/api-speak.git
git clone https://github.com/escudeler/api-third-part.git
```

## Prerequisites
You have installed:
 - JDK 11
 - Maven
 - Docker (docker-compose 1.13.0+)

## Initialize Project 
**Execute this commands on the project folder**

- In the folder where is Dockerfile, you must execute:
```sh
# build project with your dependencies
mvn clean package

# Generate image:
docker build -t escudeler/api-listen .
```

- In the folder where is docker-compose.yml, you must execute:
```sh
# up container
docker-compose up -d
```

**For logs**
```sh
docker logs -f api-listen
```

**Postman Collection**
- The Posts examples can be obtained [Here](https://www.getpostman.com/collections/ad0f4be8e0eb643d4cde).

## Specification of gRPC

API Listen's service use gRPC connection for fastest integration with **Speak** API. 
- Method: **hello** -> get response from (client of) Speak.

_More info about gRPC Communication is in [Speak Service Proto](https://github.com/escudeler/api-common-proto/blob/master/src/main/proto/SpeakService.proto/)._
