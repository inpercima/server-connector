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
cp src/main/resources/application.yml src/main/resources/application-dev.yml
cp src/main/resources/application.yml src/main/resources/application-prod.yml
```

Update these files for your environment.
Note: These files will not be under version control and listed in .gitignore.

## Usage

### Run in devMode with real data

```bash
./mvnw spring-boot:run
```

### Run in prodMode with real data

```bash
./mvnw ./mvnw spring-boot:run -Pprod
```

### Package in prodMode with real data

```bash
./mvnw clean package -Pprod

# without tests
./mvnw clean package -Pprod -DskipTests
```
