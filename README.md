## Application Details

Rest application performing very basic contract CRUD operations. 
- Structure
  - H2 in-memory database
  - MyBatis Persistence Framework
  - Liquibase database version system
  
## Running application

- Compile Jar
  - ` ./gradlew build `
- Run application
  - `java -jar build/libs/development-testapp-0.0.1-SNAPSHOT.jar`
- API root endpoint
  - localhost:8080/api/contracts
- H2 database access
  - localhost:8080/h2-console
  
## Application URIs

- Retrieve all contracts
  - localhost:8080/api/contracts
- Retrieve all approved contracts
  - localhost:8080/api/contracts/approved
- Retrieve specific contract
  - localhost:8080/api/contracts/{id}
- Create new contract
  - post localhost:8080/api/contracts/new
- Update contract
  - put localhost:8080/api/contracts/update
- Delete contract
  - delete localhost:8080/api/contacts/delete/{id}
  
## Generating the Persistance Mappers
 - Install Eclipse plugin
   - http://www.mybatis.org/generator/running/runningWithEclipse.html
 - Right click `mybatis-code-generator/generatorConfig.xml` and select Run MyBatis Generator 

## Liquibase 
- Adding and updating tables
  - add or update sql file under
    - src/main/resources/db.changelog/changesets
    - if adding new file, then add it to the change-set-master-list.yaml file
- More details can be found at http://www.liquibase.org/quickstart.html

