# Movie Rent Challange

This project is a small api for a movie rent using spring boot as core,
you can try this api on this url: https://movierent-challange.herokuapp.com

## Getting Started

There are two diferent ways to try this application one of the is locally and the other is throught heroku with the url provided before,
inside the project on resource folder you can find a postman collection to try the endpoints or in this url https://www.getpostman.com/collections/e901e14379b72db0b4b3. Endpoint information you find it https://movierent-challange.herokuapp.com/swagger-ui.html#/

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

## Runnig project with docker-compose

Open a terminal on root folder of the project and execute next command: **docker-compose up**, and the projecto will run locally.

### Using API

I'll try to explain the logic used to resolve the challange, First you have to get JWT from 'oauth/token' endpoint with admin or user credentials, the credentials are:

Admin role

username: admin
password: admin

User role

username: user 
password: user

Once you have the token you can use the other endpoints.

### Add a movie(POST)

Only Admin role can create a new movie the endpoint is: '/api/v1/movies'


### Modify a movie(PUT)

Only Admin role can modify a  movie the endpoint is: '/api/v1/movies'

**Note:** When you modify the title,rental price or sale price a log is saved with the field you change 

### Remove a movie(PATH)

Only Admin role can remove a  movie the endpoint is: '/api/v1/movies/1/remove' when remove a movie a remove date field is filled.

**Note:** after the word movies on url you have tu put the id of the movie you want to remove

### Delete a movie(DELETE)

Only Admin role can delete a  movie the endpoint is:'/api/v1/movies/1' the number is the id of the movie


### Modified availability(Patch)

Only Admin role can change the state of the movie the endpoint is '/api/v1/movies/2?state=1' you have to put the id of the movie followed of the query param 'state' where '1' is available and any other number is not available.



### Create Stock (POST)

Only Admin role can create stock for movies the endpoint is '/api/v1/movies/2/stock' where number 2 is the id of the movie you want to add more stock, if you don't add stock for a movie when you want to rent,sale or get the list of the movie you can not do those action because is mandatory have stock

### Rent a Movie (POST)

USER AND ADMIN can rent a movie the endpoint is'/api/v1/movies/2/rent' where '2' is the id of the movie you want to rent if you have enough stock available you can rent the movie.


### Sale a Movie (POST)

USER AND ADMIN can buy a movie the endpoint is'/api/v1/movies/2/sale' where '2' is the id of the movie you want to buy if you have enought stock available you can sale the movie.


### Like a Movie (POST)

Only USER role can like a movie the endpoint is'/api/v1/movies/2/like' where '2' is the id of the movie you want to like the user can like the movie just once .

### List Movies (GET)

Authenticated or not you can have a list of movies if the movies has stock, are availables and you can sorted, find by name and the results are ordered by number of likes Desending and by Title.

ADMIN role can have the list of movies available or not can paginated find by title, filter by available or unavailable

Query Params:
```
- page: number of page
- size: number of records per page
- title: find by title
- state: available (1), unavailable (0) or any other number
```
### Get Movie Details (GET)

Authenticated or not you can have movie details the enpoint is 'api/v1/movies/1' WHERE '1' is the id of the movie.

### API DOCUMENTATION (GET)

Authenticated or not you can get api documentation the endpoint is 'swagger-ui.html#/'

Hope all works well.

Best Regards

