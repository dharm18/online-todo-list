# online-todo-list
## features considered
1. User Role Based JWT authentication with signup feature
2. User can create TODOs
3. Todo list is sorted by target date in descending order
4. User can check/uncheck todo
5. User can delete the todo
6. Login/Logout feature

# To Run Jar file
java -jar todo-list-0.0.1-SNAPSHOT.jar

# To build jar package
1. Goto backend/todo-list code directory
2. RUN: 'maven clean package' in terminal/cmd.

# To regenerate angular pages
1. Goto frontend/frontend/todo code directory
2. RUN: maven clean package

# Code Explaination

The entire coding logic is divided into two main part, frontend and backend code. Frontend is developed using Angular 8 and backend using Spring Boot 2 version. These technologies have been chosen because of their ease of development.

## Front End Code Explaination
Front end code is developed using components like login, logout, menu, home, footer, todo list and todo.
Similarly, angular code is architected into multiple layer with service, constant and component layer.
Additionally, routing has been considered with active routing and current authentication.
Angular code is packaged using 'ng build' command in root directory and dist files are generated into public folder of backend code' resources folder

## Back End Code Explaination
Backend is organised into JwtAuthenticationRestController and TodoController process. Appropriately Model beans, service and repository classes/interfaces have been created. JPARepository interface is used to provide JPA features around the model classes.

H2 with hibernate is configured to store in-memory data.
Similarly, exception handling, utility, web security classes have been configured.

## Things to consider in future
1. JUnit test cases
2. angular test cases
3. e2e test cases
4. Spring Test cases
5. Code optimisation
