### Spring Data Rest

#### Introduction

* In general, Spring Data REST is built on top of the Spring Data project and makes it easy to build hypermedia-driven REST web services that connect to Spring Data repositories – all using HAL as the driving hypermedia type.
* The core functionality of Spring Data REST is to export resources for Spring Data repositories

#### Features
* Exposes a discoverable REST API for your domain model using HAL as media type
* Supports navigation via pagination links,search resources for query methods
* Client specific representation using projections
* Ships customized variant of HAL browser.

#### Implementation

* Add `spring-date-rest` dependency in build.gradle or pom.xml.
* Create DataRepository for Domain Object. For customization use `@RepositoryRestResource` annotation.
    ``` java
    @RepositoryRestResource(path = "people", collectionResourceRel = "collection" )
    public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {
    ```
  Here, we have used PagingAndSortingRepository to get paging & sorting behaviour.
  `path` is used to customize rest path for this repo & collectionResource is the name of collection.
* Start the application, & hit http://localhost:8080/ endpoint that will give you list of all available rest resources
   ```json
   {
     "_links": {
       "employees": {
         "href": "http://localhost:8080/employee{?page,size,sort}",
         "templated": true
       },
       "collection": {
         "href": "http://localhost:8080/people{?page,size,sort}",
         "templated": true
       },
       "profile": {
         "href": "http://localhost:8080/profile"
       }
     }
   }
   ```

As we can see, there is http://localhost:8080/people endpoint under `collection` resource.

Spring Data Rest by default provides `CRUD` rest resources.

##### CREATE

For creating resource, execute below code in terminal.
```console
    curl -X POST \
      http://localhost:8080/people \
      -H 'content-type: application/json' \
      -d '{
       "firstName" : "John",
       "lastName": "Doe"
    }'
```

In Response, we will be getting `HTTP status 201` & response will be like below:
```json
    {
        "firstName": "John",
        "lastName": "Doe",
        "_links": {
            "self": {
                "href": "http://localhost:8080/people/1"
            },
            "person": {
                "href": "http://localhost:8080/people/1"
            },
            "employee": {
                "href": "http://localhost:8080/people/1/employee"
            }
        }
    }
```


##### GET

For getting resource by id hit  `http://localhost:8080/people/1` respone will be like this:
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "_links": {
        "self": {
            "href": "http://localhost:8080/people/1"
        },
        "person": {
            "href": "http://localhost:8080/people/1"
        },
        "employee": {
            "href": "http://localhost:8080/people/1/employee"
        }
    }
}
```

For getting all resource hit `http://localhost:8080/people/`

##### PUT
For updating resource execute following in console.
```console
PUT /people/1 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
  "firstName" : "James",
  "lastName": "Doe"
}
```
This will update the Resource. `PATCH` is similar to PUT but partially updating the resources state.

##### DELETE
For removing resource hit `http://localhost:8080/people/1`

##### SEARCH
Spring data rest by default expose all method that are part of our repository. By hitting http://localhost:8080/search rest end point, response would be like this
```json
{
    "_links": {
        "findByLastName": {
            "href": "http://localhost:8080/people/search/findByLastName{?name}",
            "templated": true
        },
        "findByFirstNameAndLastName": {
            "href": "http://localhost:8080/people/search/findByFirstNameAndLastName{?firstName,lastName}",
            "templated": true
        },
        "relation": {
            "href": "http://localhost:8080/people/search/getByFirstName{?firstName,page,size,sort}",
            "templated": true
        },
        "self": {
            "href": "http://localhost:8080/people/search"
        }
    }
}
```
Here we are getting links for searching resource based on methods defined in repository
For example
* search by last name - http://localhost:8080/people/search/findByLastName?name=Doe
* search by first & last name - http://localhost:8080/people/search/findByFirstNameAndLastName?firstName=John&lastName=Doe
* search by first name with paging size - http://localhost:8080/people/search/getByFirstName?firstName=John&page=0&size=5
* search by first name, sorting on last name - http://localhost:8080/people/search/getByFirstName?firstName=John&sort=lastName,asc

#### Configuration
There are few properties which we can alter. We can either update this configuration using `RepositoryRestConfigurer` or in `application.properties`

| Name  | Description |
| ----- | ----------- |
| basePath | root URI for Spring Data Rest|
| defaultPageSize | default number of items served in a single page |
| maxPageSize | maximum number of items in a single page|
| pageParamName | name of the query parameter for selecting pages|
| limitParamName | name of the query parameter for number of items to show in a page|
| sortParamName | name of the query parameter for sorting  |
| returnBodyOnCreate | if a body should be returned on creating a new entity|
| returnBodyOnUpdate |  if a body should be returned on updating an entity|

