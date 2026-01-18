Flexcity Backend Test

How to run it:mvn spring-boot:run
How to execute a request POST: download a POST application(ex:Postman)
put the URL:http://localhost:8080/flexcity/activate 
and select json body with
{
	"date": "2026-01-20T16:00:00",
	"volume": 7000
}



J'ai réalisé cette API en Java par ma maitrise du langage et springboot pour sa simplicité de développement, de portabilité et d'exécution.

A l'aide d'un petit controller Rest à la réception de la request cliente pour l'activation d'assets en fonction de la date et du volume. Je retransmet la demande à mon service qui va ainsi effectuer une récupération des assets disponibles à cette date pour ensuite les trier et les sélectionner selon l'algo configurer.

Cette sélection est ensuite mapper dans une liste d'objet DTO afin de filtrer les informations retransmise.


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


J'ai appeler mes classes de données entity afin de symboliser leur appartenance à la base de données mais sans les annoters @Entity afin d'éviter la mise en place de celle-ci non nécessaire pour l'exercice. Je récupère donc un fichier de donnée Json au travers de la classe AssetRepository.

j'ai également ajouter 2 classes d'exception afin de montrer les bonnes pratiques de gestion d'erreur métier. Cela permet d'avantage de lisibilité et permet de différencier les erreurs métiers des erreurs de programmations.









Rajout possible de priorités dans les assets

rajout possible de la distinction pour les assets de production et de consommation

enum possible sur le switch case!

Décidé de séparer les 2 classes d'algo pour plus de lisibilité et de précisions dans les tests unitaires. Donc de pouvoir générer les listes d'assets en paramètre des méthodes sans avoir besoin de mocker le repo.





