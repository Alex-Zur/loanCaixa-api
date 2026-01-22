# Loan Caixa API

## Overview

This project implements a REST API for managing personal loan applications.
It allows clients to create loan requests, update their status and search the different loans with the optional filters

## Functional Scope

The API supports the following operations:

1. Create a loan application
2. Update the status of an existing loan
3. Query loan applications using filters

### Loan data

- Applicant name
- Requested amount
- Currency
- Identification document (DNI / NIE)
- Creation date
- Status

## Architecture

Layered architecture based on Spring MVC, following an API First approach using OpenAPI.”

## API First & OpenAPI

- The API contract is defined using OpenAPI in YAML format
- The YAML specification is the single source of truth
- OpenAPI Generator is used to generate API interfaces and models
- Controllers implement the generated interfaces, ensuring strict contract compliance

## Technology Stack

- Java 17
- Spring Boot
- OpenAPI / Swagger (YML-based)
- Maven
- JUnit
- Mockito

## Tools
 
 - https://forge.etsi.org/swagger/editor/
 - https://start.spring.io/
 - Spring tools 5.0.1 Release

## Running the Application

### Build and run

 - mvn clean install 
 
 - LoanCaixaApiApplication.java -> Run as -> Spring Boot APP 

Application available at:

 - http://localhost:8080/swagger-ui/index.html
 
If you want to use it in Postman or something similar

 - src/test/resources/loanCaixa-api.postman_collection.json


## API Documentation

Swagger UI is automatically generated from the OpenAPI YAML specification and is available at:

## Testing

- Unit tests for service and controller layers

## Possible Improvements

- Persistent storage
- Global exception handling
- Integration tests (MockMvc)
- API releases and security

