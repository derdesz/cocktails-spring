# Soda-Licious Cocktail site (backend service)

You can find the frontend repository here: https://github.com/derdesz/cocktails

Our cocktail site is an application for searching and filtering cocktail recipes, adding new ones and saving your favorites into the database. 
Search by name or ingredient; filter by alcoholic/non-alcoholic property or spirits and view ingredients and instructions of the cocktails.

Backend was created in Spring framework, data is strored in Hibernate database.
Spring security was also added in order to provide option to register and log in. After a successful login the user can add their own recipes or mark their favorite cocktails.
Starting cocktail data was imported to database from this API: https://www.thecocktaildb.com/api.php?ref=apilist.fun. 

Future plans: 
- corrections in security
- image upload

### Running the application
This is a maven project, so `pom.xml` will install all the dependencies you need in order to run this application.  
To start the application run 
```
./mvnw spring-boot:run
``` 
command in terminal in the root directory of the project.
The application needs a H2 database, the connection parameters are specified in the `application.properties`. In case another database is preferred configure `application.properties` accordingly before starting the application.
