# The challenge (Fullstack Engineer)

This is repository for code written as part of interview process. It contains backend written in Java/Spring and frontend written in React.

## Running project

Both frontend and backend have its own Docker files and one Docker compose, so before trying to execute bellow commands make sure
that you have Docker desktop installed.
To run project execute following commands:

```bash
docker compose up --build
```

### Running components separately

To run backend execute following commands

```bash
cd chat-service
mvn spring-boot:run

```

To run frontend execute following commands

```bash
cd chatty-frontend
npm install
npm start
```

## Technologies used

Backend is developed using:

- Java 17
- Spring Boot
- MongoDB

Frontend is developed using:

- React
- Typescript
- react-scripts

## Future steps

Some improvements that could be added in future:

- Backend:

  - Proper authorization and authentication
  - Swagger documentation
  - Integration tests

- Frontend
  - Proper authorization and authentication
  - Error handling
  - Write tests
