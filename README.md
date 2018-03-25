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
         "href": "http://localhost:8085/employee{?page,size,sort}",
         "templated": true
       },
       "collection": {
         "href": "http://localhost:8085/people{?page,size,sort}",
         "templated": true
       },
       "profile": {
         "href": "http://localhost:8085/profile"
       }
     }
   }
   ```

As we can see, there is http://localhost:8085/people endpoint under `collection` resource.

Spring Data Rest by default provides `CRUD` rest resources.

##### Create
    For creating resource, execute below code in terminal.
    ```bash
    curl -X POST \
      http://localhost:8085/people \
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
                "href": "http://localhost:8085/people/1"
            },
            "person": {
                "href": "http://localhost:8085/people/1"
            },
            "employee": {
                "href": "http://localhost:8085/people/1/employee"
            }
        }
    }
    ```