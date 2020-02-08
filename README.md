## groot-data
SpringBoot Data JPA persistance-API, using H2database, Junit5
 
Java Application for Friends of Groot Society
<a style="margin-left:20%;" href="https://friends-of-groot-society.s3-website-us-east-1.amazonaws.com
">
<img width="200" src="https://friends-of-groot-society.s3.amazonaws.com/assets/grootsmall.png" title="Friends_of_Groot_Society_App" alt="Friends_of_Groot_Society_Image"></a>

### Groot Society Fan Club Groot News Tracker 
> <a style="text-decoration:none;color:black;" href="http://friends-of-groot-society.s3-website-us-east-1.amazonaws.com/grootedex">Friends_of_Groot_Society</a>

> Full-Stack application that manages Groot Fan Club News and Features
 
### Author 
> Thomas Maestas 

### Application Overview
The mission of the app is to organize and persist Groot Fan Club News and Guardians-of-the-Galaxy Events

#### Purposes
The mission of the app is to organize and persist Groot Fan Club News and Guardians-of-the-Galaxy Events

### Technologies 
| Fx | Tools | URLS |
|-----------------|:-----------------:|---------:|
| Database | Oracle SE 11 | [Oracle]  | 
| Cloud Data | Amazon RDS |  [AWS-RDS] | 
| Cloud ASsets | Amazon S3 |  [AWS-S3]  |
| User Data | Angular 8 |  [Angular]  |
| UI/UX | Angular-Bootstrap |  [BS4] |
| E2E Testing | Selenium | [Selenium] |
| CI/CD | Jenkins | [Jenkins] |
| Pipeline | AWS Codebuild | [CodeBuild] |
   
##### Angular8: 
Typescript development Framework that integrates HTML5 templates, CSS styling, which compiles Typescript into JavaScript using WebPack, taskrunner module bundler
##### Jenkins:  
Open source automation server which enables us to reliably build, test, and deploy their software-->grabs .
#####  AWS Relational Databases
Cloud-based dynamic servers hosting Apache Tomcat, dataBase endpoints
##### AWS EC2:  
hosting application for Jenkins to run tests
##### AWS S3  
hosts static assets like our 
##### AWS CodeBuild:  
fully managed continuous integration service that compiles #source code, runs tests, and produces software packages 
##### AWS RDS:  
set up, operate, and scale a DB in the cloud. Cost-efficient and resizable capacity automating administration tasks such as hardware provisioning, database setup, patching and backups. 
##### PostMan
complete API development environment. Today we have 8 million developers and over 400K companies using our comprehensive set of built-in tools to support every stage of the API lifecycle. With Postman you can design, mock, debug, test, document, monitor, and publish your APIs all in one place.
#### TomCat Server
Apache Tomcat is an open-source implementation of the Java Servlet, JavaServer Pages, Java Expression Language and WebSocket technologies. Tomcat provides a "pure Java" HTTP web server environment in which Java code can run.
#### Hibernate Object Relational Mapper
ORM enables developers to more easily write applications whose data outlives the application process. As an Object/Relational Mapping (ORM) framework, Hibernate is concerned with data persistence as it applies to relational databases (via JDBC)
#### Testing Libraries:
```json
"Jenkins" : "2.0",
"codelyzer": "^5.0.0",
"jasmine-core": "~3.4.0",
"jasmine-spec-reporter": "~4.2.1",
"karma": "~4.1.0",
"karma-chrome-launcher": "~2.2.0",
"karma-coverage-istanbul-reporter": "~2.0.1",
"karma-jasmine": "~2.0.1",
"karma-jasmine-html-reporter": "^1.4.0",
"protractor": "~5.4.0",
"ts-node": "~7.0.0",
"tslint": "~5.15.0",
```
#### Application User Stories
```sh 
As an society-administrator, I can login.
As an society-administrator, I can add news-bulletins.
As an society-administrator, I can add notes about Groot and the Guardians.
As an society-administrator, I add notes about Groot and the Guardians news-bulletins.
As an society-administrator, I can view my upcoming news-bulletins.
As an society-administrator, I can view information about the client.
As an society-administrator, I can view information about Groot and the Guardians.
As an society-administrator, I can add society-member (fan)s.
As an society-administrator, I can add pets to the society-member (fan)
As an society-administrator, I can add vaccinations to an pet
As an society-administrator, I can view the weights of Groot and the Guardians on the dates of their news-bulletins.
-----------
As a society-member (fan), I can login to an account.
As a society-member (fan), I can fill out a form with my information.
As a society-member (fan), I can see notes on what my pet was treated for.
As a society-member (fan), I can view a list of my owned pets.
As a society-member (fan), I add a new pet.
As a society-member (fan) I can book an news-bulletins    
As a society-member (fan), I can upload relevant documents about my dog into the system.
As a society-member (fan), I download copies of my vaccination/health records.
As a society-member (fan), I can see the age of my pet. 
--------------
As a system, it will notify us in some way if the vaccination record has expired.
As a system, it will notify us if the vaccination record will expire before the scheduled visit.
```
 
** Software **

* [Oracle]: <https://www.oracle.com/database/technologies/112010-win64soft.html>
* [AWS-RDS]: <https://aws.amazon.com/rds/>
* [AWS-S3]: <https://aws.amazon.com/s3/>
* [Angular]: <https://angular.io/>
* [BS4]: <https://numpy.org/>
* [Selenium]: <https://selenium.dev/documentation/en/>
* [Jenkins]: <https://jenkins.io/> 
* [CodeBuild]:<https://aws.amazon.com/codebuild/> 
   
  website: [doggywood.io](http://doggywood.s3-website-us-east-1.amazonaws.com/t)
 

#### URLS for Angular/Material:
#### https://material.io
#### https://angular.io 
This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 8.3.21. 


### INSTRUCITONS:  Development server
 
## JSON SERVER
npm install -g json-server
json-server info.json --watch

 
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

 

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 8.3.21.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

