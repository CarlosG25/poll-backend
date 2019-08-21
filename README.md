# Poll api 
Back-end for poll web app

### Table of Contents
1. [Create the environment for dev](#Pre-requisites)
    1. [Docker](#Docker)
    1. [Docker-compose](#Docker-compose)
2. [Development environment](#Preparing-the-development-environment)
3. [Playground](#Playground)
4. [Spring Boot](#Spring)

## Pre requisites
### Docker
To install docker in ubuntu 16.04
```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

sudo apt-get update

apt-cache policy docker-ce

sudo apt-get install -y docker-ce
```

In case of ubuntu 18.04
```bash
sudo apt-get install -y docker.io
```

### Docker-compose
```bash
sudo curl -L "https://github.com/docker/compose/releases/download/1.23.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose
```

## Preparing the development environment
To buld the new containers use the following command, the **--build** flag will force the containers to be rebuilt instead of using the previous ones
```bash
docker-compose up --build -d
```

To tear down the currently running containers
```bash
docker-compose down -v
```

Alternatively both commands may be chained should a problem arise with the containers
```bash
docker-compose down -v && docker-compose up --build -d
```

Is necessary to create env, for this you can create a copy of sample.env
```bash
 cp sample.env .env
```

###  Users Test
```javascript
 Users = "admin", "user";
 Password = "Admin"
```

###  Url Back-end
```javascript
http://localhost:8082
```

[frontend link]: URL

## Spring

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Web Starter](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-mongodb)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-jpa-and-spring-data)




