# Movie Rent Challange

This project is a small api for a movie rent using spring boot as core,
you can try this api on this url: https://movierent-challange.herokuapp.com

## Getting Started

There are two diferent ways to try this application one of the is locally and the other is throught heroku with the url provided before,
inside the project on resource folder you can find a postman collection to try the endpoints.

### Prerequisites

- Java 8
- Maven
- Postgres 


### Installing

Firstable you have to get install a postgres database and create a database named movierent.
Then to install the project locally, you have to open a terminal and then go to folder project and execute the following commands

- mvn clean install
- java -jar movie-rent-applaudo-challange-0.0.1-SNAPSHOT.jar

Go to the database and verify if you have created the following to tables 'oauth_access_token'  and 'oauth_refresh_token' 
if you don't have created those tables go to the folder project resource and you will find 'data.sql' execute the script ,the script will
create two tables  and two user: admin and user.

