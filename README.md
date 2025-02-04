# server-connector

[![MIT license](https://img.shields.io/badge/license-MIT-blue.svg)](./LICENSE.md)
![Github Action](https://github.com/inpercima/server-connector/workflows/Java%20CI/badge.svg)

A very simple ssh server connector to run commands remote.

## Prerequisites

### Java

* `jdk 21` or higher

## Getting started

```bash
# clone project
git clone https://github.com/inpercima/server-connector.git
cd server-connector
```

Create environment files for `devMode` and `prodMode`.

```bash
cp src/main/resources/application.yml src/main/resources/application-dev.yml
cp src/main/resources/application.yml src/main/resources/application-prod.yml
```

**Note**: These files will not be under version control but listed in .gitignore.

## Usage

### Run in devMode

```bash
# short
./mvnw

# long
./mvnw spring-boot:run -Pdev
```

### Run in prodMode

```bash
# short
./mvnw -Pprod

# long
./mvnw spring-boot:run -Pprod
```

### Package and run in prodMode

```bash
# package
./mvnw clean package

# package without tests
./mvnw clean package -DskipTests

# place the `application-prod.yml` aside the server-connector-1.0.0-SNAPSHOT.jar and run the jar
cp src/main/resources/application-prod.yml target/application-prod.yml
cd target
java -jar server-connector-1.0.0-SNAPSHOT.jar --spring.profiles.active=prod
```

## Configuration

### General

All options can be overridden in application-*.yml with prefix `app.`.
For example, the port is 22 by default, so it does not need to be overridden.

### Table of contents

* [host](#host)
* [password](#password)
* [port](#port)
* [user](#user)
* [commands](#commands)

### `host`

The host to be connected.

* default: `host`
* type: `String`

### `password`

The password for the connection.
Attention: The password is in plaintext.

* default: `password`
* type: `String`

### `port`

The port for the connection.

* default: `22`
* type: `Integer`

### `user`

The user for the connection.

* default: `user`
* type: `String`

### `commands`

The commands to be send and executed on the server.
This is a list of commands with minimum one command.

* default: `ls`
* type: `String`

Example:

```bash
app:
  commands:
    - cd /home
    - ls
```
