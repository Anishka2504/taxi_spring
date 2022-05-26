### taxi_spring

Folder: https://github.com/Anishka2504/taxi_spring

# Taxi depot application
![img_1.png](img_1.png)

An educational project created with Spring Boot. 
The application use MySQL: 5.7.35.

Hibernate ORM 5 https://hibernate.org

SpringBoot framework 2.6.7 https://spring.io

Mapstruct 1.4.2 Final https://mapstruct.org



## About

The "Taxi Depot" application allows to:

- add cars and drivers to the database;
- remove cars and drivers from the database;
- make search of data using particular parameters (equipment for cars, last name for drivers etc) and get them in
  sorted;
- attach drivers to cars and unpin drivers from cars;
- change necessary parameters for cars and drivers;
- count total cost of the depot.

## Launch

Before launch, make sure that your device support MySQL. Install software for database developing and administrating if
necessary (PHP Adminer, Postgres, Workbench etc).

1. Check connection to the database.

2. Run TaxiDepotApplication.

If the application doesn't fill the database automatically:

1. Run _dump.sql_ file (taxi_spring\api\src\main\resources\sql\dump.sql) to create the database and to fill it with data.

2. Run TaxiDepotApplication file (taxi_spring\api\src\main\java\edu\itstep\taxi\TaxiDepotApplication).

For interaction with the application use Swagger UI:

- open your browser and enter _localhost:8080/swagger-ui/index.html_ in the adress bar. Or go to link:
   - http://localhost:8080/swagger-ui/index.html
   
Edit configurations in your IDE if necessary.