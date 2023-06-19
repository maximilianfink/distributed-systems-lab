# Distributed Systems - Lab - Maximilian Fink 

Lab part of the module "Distributed Systems" at Esslingen University in the summersemester 2023

Author: Maximilian Fink (764001)

## Simple start with Docker

`docker-compose up -d`

Access the application: 

- Frontend URL: http://localhost
- Backend URL: http://localhost:8080
- Swagger URL: http://localhost:8080/swagger-ui/

All parameters can be customized in the docker-compose env area!

## Description

This project is a web application consisting of a backend with a MySQL database and a frontend. The backend is built using Java Spring Boot and provides RESTful APIs for managing a todo list. The frontend is built using Vue.JS and provides a simple user interface to interact with the backend APIs.

### Features

- Add new todos
- Delete todos
- Edit existing todos
- Mark todos as done


### Technologies / Frameworks used

* Java Spring Boot
* MYSQL Database
* Vue.JS
* Docker
* REST
  
## Start with Docker and rebuild the Images (Dev)

Force a rebuild and use another compose file:

`docker-compose -f docker-compose-build.yml up --build`

## Build the Images

Build the backend:

`docker build -t maximilianfink/vslab-backend:latest ./backend`

Push the backend to the docker hub:

`docker push maximilianfink/vslab-backend:latest`

Build the frontend:

`docker build -t maximilianfink/vslab-frontend:latest ./frontend`

Push the frontend to the docker hub:

`docker push maximilianfink/vslab-frontend:latest`


## Development (without Docker)

### Requirements 

* Computer with VS Code installed
* Postman is helpful
* JDK 17 installed (https://www.oracle.com/java/technologies/downloads/#jdk17-mac)

### How to start 

* mvn install
* mvn spring-boot:run
* started at http://localhost:8080

