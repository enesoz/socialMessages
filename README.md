#### socialMessages

The purpose of this project made is learning some technologies and improve my code skills , Here example project for microservices with eureka , spring boot social api , vue.js as frontend tech , mongodb (nothing so much) and vs.

## Setup Project

### Prerequisites

*mongodb  

*maven

*lombok intellij idea settings (If you use intellij idea)

*nodejs

### Project Setup


```
socialMessages
├─┬ backend     → backend module with Spring Boot stuff , eureka client
│ ├── frontend  → frontend module with Vue.js stuff
│ └── pom.xml
├─┬ message-service    → microservices include spring-social api and mongo , eureka client
│ └── pom.xml
├─┬ registration   →  register module with eureka server
| └── pom.xml
└── pom.xml     → Maven parent pom with modules
```

 ## After clone 

```
cd $project.basedir
mvn clean install 
mongod   // @see note1
java -jar registration\target\registration.0.0.1-SNAPSHOT.jar 
java -jar message-service\target\message-service.0.0.1-SNAPSHOT.jar 
java -jar backend\target\backend.0.0.1-SNAPSHOT.jar 
cd backent\fronted
npm run dev     //@see note2
```

*note1 : there is no specific mongo settings - if you want to use ,change message-service \resources\application.properties
*note2 : [build, prod] which profile to use  you want 

### Paths 

This Application Path : localhost:8080/
#### No need to know
*Eureka Server Path    : localhost:1111/
*Message-Service Path  : localhost:2222/
*Backend  Path         : localhost:3333/

# Here it is

![localhost-first-run](https://github.com/enesoz/socialMessages/blob/master/backend/frontend/src/assets/localhost-first-run.PNG)