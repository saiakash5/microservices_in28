# microservices_in28

## Project Description

This entire project was implemented based tutorial in28minutes. 

In this project initially we need to spin up Netflix Eureka Naming server. Eureka naming server itself is a SpringBoot application. We need to configure naming server's name, port and we need to setup false for eureka registration for this particular application.
Basically Eureka is used for registering all other services that needed to inter comunicate with each other. In this case currency-conversion-service calls currency-exchange-service for conversion rate and converts based on that value to INR from other currencies. In order to achieve that we need spin up Eureka Naming server initially.


### Currency-Exchange-Service

As I mentioned earlier this service is used for giving conversion rate for equivalent to INR from other currencies. In this service I have used H2 database which is in-memory database and populated database using data from data.sql file which you can find in resources folder. We have repository folder which consists an interface extending JpaRepository for object persistence. We have bean directory consisting model class for currency exchange service which is mapped to JpaRepository. And we have a controller for handling simple @GetMapping for retrieving conversion rate.


### Currency-Conversion-Service

In this service we can find simple model which consists all fields from currency-exchange-service and additional fields such as quantity and totalAmount which are needed to be calculated after retrieving conversion rate from conversion-exchange-service. We have a proxy directory which is used for making rest call to currency-exchange-service. In naive terms we need to copy entire method signature from currency-exchange-service which we are trying call and change return type to model of our current service. We autowire this particular interface object and map all the values from URI and return total amount with new object created.

### Netflix-Zuul-API-Gateway-Server

Api gateway is a service which maintains all calls between micro-services. Such as currency-conversion-service depends on currency-exchange-service so this particular call is routed through api gateway. This satisfies two purposes, one is all communication are done through this service which makes implementation lot easier, second thing is authorization and authentication is implemented in here. 

### Ribbon server

We use Ribbon server for load balancing in our application. We don't have separate service for ribbon its implementation is different. In our application lets say we have two instances of currency-exchange-service running on port 8000, 8001 and so on, depending on number of requests. In order to balance load we use Eureka naming server along with Ribbon. Basically ribbon balances load based on number of instances which are registered on Eureka-Naming-Server service at that moment of time.


## Process of execution

#### Step1 : Spin up eureka-naming-server.
#### Step2 : Spin up two instances on currency-exchange-service. In order to do that you need to create new configuration with VMArgument as -Dserver.port = 8001.
#### Step3 : Spin up netflix-zuul-api-gateway service.
#### Step4 : Spin up currency-conversion-service.
#### Step5 : Check all the application are registered with Eureka by navigating to http://localhost:8761 
#### Step6 : Make rest calls accordingly and you can find the required results.
