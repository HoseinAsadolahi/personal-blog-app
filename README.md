# Personal Blog App

## A simple personal blog app written in java

- a simple solution to [roadmap.sh](https://roadmap.sh) backend project named [personal blog](https://roadmap.sh/projects/personal-blog)

- For managing data I used postgresql
- I used flyway as a migration tool
- thymeleaf and hibernate and etc.

## how to use

- first clone the project
```shell
git clone https://github.com/HoseinAsadolahi/personal-blog-app
cd personal-blog-app
```
- after that you need to create a new database
- after that you need to create a new user and grant authorities over that database
- now you need to create a file named 'application.properties' in './src/main/resources' like below:
```
spring.application.name=Blogging App

spring.datasource.url = jdbc:postgresql://localhost:5432/<your-database-name>
spring.datasource.username=<database-admin-username>
spring.datasource.password=<database-admin-password>
spring.jpa.database=postgresql
spring.jpa.database-platform = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.defer-datasource-initialization=false
```
- and now you can open your project using intellij for example and run it
- add admin details:
```sql
insert into users(email, first_name, last_name, password, role)
values ('blogadmin', 'Blog', 'Admin', 'password', 'ROLE_ADMIN');
```
- go to [this page](https://localhost:8080)

## routs
- for managing your blog you can go to [dashboard](https://localhost:8080/dashboard)
- from there you can add, update, delete and check the articles
- you can also visit [home page](https://localhost:8080)
- in there you can sort the articles based on likes or publication date
- if you go to a article you can see its content
- if you want you can like or if liked, you can unlike it
- you can post comment there.
- liking and commenting requires authentication
- so you need to [create an account](https://localhost:8080/signup) and [login](https://localhost:8080/login) with it