FROM amazonlinux:latest as build

WORKDIR /opt/gradle

RUN yum install java-17-amazon-corretto-devel zip findutils -y && \
    curl -L -O https://services.gradle.org/distributions/gradle-8.9-bin.zip && \
    echo d725d707bfabd4dfdc958c624003b3c80accc03f7037b5122c4b1d0ef15cecab  gradle-8.9-bin.zip | sha256sum -c - && \
    unzip gradle-8.9-bin.zip

ENV GRADLE_HOME=/opt/gradle/gradle-8.9
ENV PATH=${GRADLE_HOME}/bin:$PATH

WORKDIR /app

COPY . .

RUN gradle clean build -x test

FROM amazonlinux:latest

RUN yum install -y java-17-amazon-corretto-headless \
    shadow-utils &&\
    useradd spring &&\
    yum remove shadow-utils -y &&\
    yum clean all

USER spring

WORKDIR /app

COPY --from=build app/build/libs/fetch-a-friend-backend-0.0.1-SNAPSHOT.jar fetch-a-friend-backend-0.0.1-SNAPSHOT.jar

CMD java -jar \
 -Dspring.profiles.active=prod \
  fetch-a-friend-backend-0.0.1-SNAPSHOT.jar