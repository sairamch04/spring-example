# spring-example

Very basic spring application to get basic idea of PRG(POST-REDIRECT-GET) pattern, Spring validators,Transaction management and how to use Postgres database.
```
-- Run following queries in postgres db
CREATE database sample;
\c sample;
CREATE SCHEMA training;
SET search_path to training;
CREATE TABLE users (
    user_id   SERIAL PRIMARY KEY NOT NULL,
    user_name character varying(100) NOT NULL,
    last_usage_time timestamp without time zone
);

```
## App does the following
i) Add new user  /users/add [POST]
ii) Show the last visit of user when visited /users/{id} [GET] , for a new user show "Welcome ${userName}"

### How to setup
Build the project using maven and deploy the war file in Tomcat

``` mvn install ```
