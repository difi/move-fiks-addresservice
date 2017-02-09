# MOVE Fiks addresseservice

Service to lookup adressing information needed to send messages to a provided organization.

This application is supposed to be used by Service Registry to create adressing information needed by an 
Integrasjonspunkt. The application is a simple crud application exposed via a simple http api. Organizations can be looked up returning the url of the service provider and that service public certificate.

It is intended to be used for routing messages to FIKS SvarUt but it can just as well be used for routing to other services. 

## Starting
If you have built the jar you just be able to start the application with
java -jar move-fiks-adresse*.jar

Default ports: 9100 and 9101 (management)

## Management
The application uses actuator so that it can easily be studied/monitored/managed.

All actuator endpoints are secured except for /mappings, /docs and /info.
