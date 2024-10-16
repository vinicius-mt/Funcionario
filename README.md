# Employee API

This microservice was developed to manage employees

## Start Local DataBase
Install Docker and un this command

docker run -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres

then, start application

## Postman Collections

### Save Employee
curl --location 'http://localhost:8080/api/employee' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Jose",
"lastName": "Costa",
"email": "jose@gmail.com",
"nisNumber": "12345678910"
}'

### Get All Employee
curl --location 'http://localhost:8080/api/employee'

### Get Employee By Id
curl --location 'http://localhost:8080/api/employee/1'

### Update Employee
curl --location --request PUT 'http://localhost:8080/api/employee/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Marta",
"lastName": "Costa",
"email": "marta@gmail.com",
"nisNumber": "12345678910"
}'

### Delete Employee
curl --location --request DELETE 'http://localhost:8080/api/employee/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Vinicius2",
"lastName": "Tocafundo",
"email": "vinicius2@gmail.com",
"nisNumber": "12345678910"
}'


