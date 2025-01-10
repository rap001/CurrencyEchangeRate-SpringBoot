**Application Overview**
This application uses SpringBoot and ExchangeRate-API to fetch real-time exchange rates of currencies.
-----------------------------------------------------------------------------------------------------
**Configuration**
Create a file at src/main/resources/application.properties with the following content:

spring.application.name=exchange
server.port=8080
exchangerate.api.key=api_key
logging.level.org.springframework=INFO
logging.file.name=application.log

------------------------------------------------------------------------------------------------------
**Service Layers**:
User Service Layer: Interacts with Api requests and responses.

**Control Layer** :
Responsible for handling user requests and responses.

**Instructions**:
1.Clone the repository.

2.Create application.properties as described in the configuration section.

3.Run the application.

------------------------------------------------------------------------------------------------------
**Tools**:
Use tools like **Postman** for easier access to the application.
