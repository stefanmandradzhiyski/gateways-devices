# gateways-devices

Programming language: Java 11

Framework: Spring

ORM: Hibernate

Database: MySQL

Building tool: Maven

Additional tools/libraries: Lombok, Mapstruct, Swagger, Flyway

The provided gateway management functionalities:
- Create a new gateway using HTTP POST request against api/v1/gateways;
- Get a specific existing gateway using HTTP GET request against api/v1/gateways/{gatewayId};
- Get all available gateways using HTTP GET request against api/v1/gateways;
- Update a specific gateway data using HTTP PUT request against api/v1/gateways/{gatewayId};
- Assign or unassign list of devices to gateway using HTTP PUT request against api/v1/gateways/{gatewayId}/devices-association;
- Delete existing gateway using HTTP DELETE request against api/v1/gateways/{gatewayId}.

The provided device management functionalities:
- Create a new device but without the ability to connect to a gateway using HTTP POST request against api/v1/devices;
- Get a specific existing device using HTTP GET request against api/v1/devices/{deviceId};
- Get all available devices using HTTP GET request against api/v1/devices;
- Update a specific device data but without the ability to connect to a gateway using HTTP PUT request against api/v1/devices/{deviceId};
- Delete existing device using HTTP DELETE request against api/v1/devices/{deviceId}.

Concerns:
Assign/unassign a device to gateway isn't possible because the idea is for the gateways to make this relation.

Bonus:
The application has pre-prepared sample data.

Setup:
- Import the project as Maven one;
- Apply JDK 11;
- Enable Annotation Processors;
- Do "mvn clean install";
- Open file application-development.yml and replace values of 'spring-datasource-username', 'flyway:user', 'spring-datasource-password', 'flyway:password' with your MySQL Server credentials;
- Run GatewaysDevicesApplication.java;
- Use http://localhost:8080/swagger-ui/index.html#/ in your browser and you're ready to test the application.
