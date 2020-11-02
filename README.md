## Marshmallow Java Backend Test

RobotCleaner is a RESTful service currently comprising a single POST endpoint. 
This endpoint retrieves the final position of the cleaner in the map area together with the number of oil patches that it has cleaned.

The goal of the program is to take the room dimensions, the locations of the oil patches, the inital location and the navigation instructions as input and then output the following:

1. The final position of the robot (X, Y)
2. The number of oil patches that it managed to clean

#### Prerequisites

Installed: Docker, Java 1.8, Maven 3.x

#### Running the application in docker

##### Build project with Maven

The first step is to build the application using maven. This will run the unit tests and produce the build artifact
if successful: 

```
$ mvn clean install
```

#### Build the docker image

To build the local docker image locally include the path to the local docker file (Dockerfile_local) in the docker build 
command:  

```
$ docker build -f Dockerfile_int -t robot-cleaner-api .
```
#### Run the docker image

Run the docker image using the following command:

```
$ docker run robot-cleaner-api
```
Tomcat runs on port 8080 as default however we can map this to any free port we choose.

##### Run the application without docker

To run the application locally without docker, run the following command inside the project

```
mvn spring-boot:run
```

#### Test the application

To test the application, once the app is up and running, you can send POST requests through Postman
In the url add localhost:8080/navigation and in headers add instructions based on the project description, e.g.
```json
{
  "areaSize" : [5, 5],
  "startingPosition" : [1, 3],
  "oilPatches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "navigationInstructions" : "NNESEESWNWWSSS"
}
```
The response is should be either a valid Response, e.g.
```json
{
    "finalPosition": [
        1,
        1
    ],
    "oilPatchesCleaned": 0
}
```
or an Exception with an appropriate error message.