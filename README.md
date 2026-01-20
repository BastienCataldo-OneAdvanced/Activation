FLEXCITY BACKEND TEST

This project is a backend technical test developed in Java using Spring Boot.

HOW TO RUN THE PROJECT

Prerequisites:

* Java JDK 25 installed
* Maven installed and configured in environment variables
* Git

Steps:

1. Clone the project from Git
2. Open a command terminal
3. Go to the project directory
4. Execute the following command:
mvn spring-boot:run



HOW TO EXECUTE A POST REQUEST(i used Postman for example)

Endpoint:
POST [http://localhost:8080/flexcity/activate](http://localhost:8080/flexcity/activate)

JSON body example:

{
"date": "2026-01-20T16:00:00",
"volume": 7000
}


APPLICATION FLOW

Client
 ↓
Route: /flexcity/activate
 ↓
RestController: ActivationController
 ↓
Service: AssetSelectionService
 ↓
Repository: AssetRepository
 ↓
DataBase simulate by Json file
 ↓
Service (algo of asset selection)
 ↓ mapping in DTO object
Controller
  ↓
response with DTO object list


TECHNICAL CHOICES

Language:
Java was chosen due to strong proficiency with the language.

Framework:
I chose Spring Boot for its simplicity of development, portability, and execution


CONTROLLER LAYER

Using a small REST controller, the client request for asset activation based on date and volume is received. Then, I send the request to my service layer.
All business logic is delegated to the service layer.


SERVICE LAYER

The service:

* retrieves available assets for the requested date from the repository layer
* sends them to the factory which select the algorithm configured by a property
* then, selects the assets to activate
* maps the results into DTO objects
Finally send it back to the client


I named my data classes entities to reflect their association with the database, but I did not annotate them with @Entity in order to avoid setting up a database, which was unnecessary for this exercise. Instead, I retrieve a JSON data file through the AssetRepository class.
I also added two exception classes to demonstrate best practices in handling business errors. This improves code readability and makes it possible to distinguish business-related errors from programming errors.
I decided to separate the two algorithm classes to improve readability and provide greater precision in the unit tests.
I made a factory of these algo classes selection to illustrate the separation of responsabilities and to start projet with long term vision.
I use Mockito in my unit tests to avoid relying on a heavy factory setup and to make the test values easier to read and understand.
I made an enum for exception in the case of multi langage project exploitation,but in our simple case, static constant would be more accurate.