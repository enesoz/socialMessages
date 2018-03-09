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


