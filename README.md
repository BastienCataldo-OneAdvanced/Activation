Flexcity Backend Test



How to run it:

get the project from GIT
install maven on your computer (change your environnement variable)
install jdk 25
Go to your project
open a command terminal
then execute mvn spring-boot:run

How to execute a request POST:

download a POST application(ex:Postman)
put the URL:http://localhost:8080/flexcity/activate 
and fill json body with
{
	"date": "2026-01-20T16:00:00",
	"volume": 7000
}



I developed this API in Java due to my strong command of the language, and I chose Spring Boot for its simplicity of development, portability, and execution.

Using a small REST controller, the client request for asset activation based on date and volume is received. Then, I send the request to my service layer, which retrieves the assets available on that date, send that list to my calcul algorithm to select the right assets to activates.
This selection is then mapped into a list of DTO objects in order to filter the information being returned.


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

I named my data classes entities to reflect their association with the database, but I did not annotate them with @Entity in order to avoid setting up a database, which was unnecessary for this exercise. Instead, I retrieve a JSON data file through the AssetRepository class.
I also added two exception classes to demonstrate best practices in handling business errors. This improves code readability and makes it possible to distinguish business-related errors from programming errors.
I decided to separate the two algorithm classes to improve readability and provide greater precision in the unit tests.
I made a factory of these algo classes selection to illustrate the separation of responsabilities and to start projet with long term vision.
I use Mockito in my unit tests to avoid relying on a heavy factory setup and to make the test values easier to read and understand.
I made an enum for exception in the case of multi langage project exploitation,but in our simple case, static constant would be more accurate.





