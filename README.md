# IDT <i>User service</i>

**The application is an implementation of the following requirements:**

The server should implement these two API calls:

* `GET /user/{id}`

Returns user details based on the id. You can return static mock results.

* `PUT /user/{id}`

Modifies a user's property. It's ok to only store changes only in memory.

## Run settings

`./mvnw spring-boot:run`

## Security

One in-memory user is `user / user`

## Metrics

Actuator dependency is used so try to hit http://localhost:8000/api/actuator/metrics to get useful metrics

## Swagger

You can get an access to API documentation through the url http://localhost:8000/api/swagger-ui.html