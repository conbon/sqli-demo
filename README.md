# sqli-demo
SQLi demo for WANdisco Data Academy

Run database:
```
docker-compose up
```

Run application:
```
mvn spring-boot:run
```

### heroku
To spin up a hosts application & database, sign up to heroku.
Once you have a database created, replace the variables found in application.properties file:
* spring.datasource.url={jdbc-url}
* spring.datasource.username={username}
* spring.datasource.password={password}
* spring.flyway.url={jdbc-url}
* spring.flyway.user={username}
* spring.flyway.password={password}

### localhost
application-dev.properties should be sufficient to connect to a local instance of postgres after running 
the docker-compose command.