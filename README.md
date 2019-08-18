# server-connector

A very simple ssh server connector to run commands remote.

## Prerequisites

### Java

* `jdk 8` or higher

## Getting started

```bash
# clone project
git clone https://github.com/inpercima/server-connector.git
cd server-connector
```

Create property files for `devMode` and `prodMode`.

```bash
cp src/main/resources/application.properties src/main/resources/application-dev.properties
cp src/main/resources/application.properties src/main/resources/application-prod.properties
```

Update these files for your environment.
Note: These files will not be under version control and listed in .gitignore.
Note: These files will not be included in the package.jar.

## Usage

### Run with wrapper in devMode

```bash
./mvnw spring-boot:run
```

### Run with wrapper in prodMode

```bash
./mvnw ./mvnw spring-boot:run -Pprod
```

### Package with wrapper in prodMode

```bash
./mvnw clean package -Pprod

# without tests
./mvnw clean package -Pprod -DskipTests
```

### Run jar in prodMode

Place the `application-prod.properties` aside the package.jar.

```bash
java -Dspring.profiles.active=prod -jar server-connector-0.0.1-SNAPSHOT.jar
```

## Configuration

### General

All options can be overwritten in application-*.properties with prefix `app.`.
For example the port is default 22 so he do not needs to be overwritten.

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
app.commands[0]=cd /home
app.commands[1]=ls
```
