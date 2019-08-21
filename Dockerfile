FROM alpine:3.10

LABEL maintainer = "Carlos Alexis Galaviz Rosas <carlosalexis.galavizrosas@gmail.com>"

USER root
COPY . /usr/src/app
WORKDIR /usr/src/app

RUN apk update && apk add gradle=5.4.1-r0

RUN gradle build -x test

CMD ["java", "-jar", "/usr/src/app/build/libs/poll-0.0.1-SNAPSHOT.jar"]
