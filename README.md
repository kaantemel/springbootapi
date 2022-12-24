
# Spring Boot API
This project creates two relational tables named Students and Projects. It is possible for a project to have more than one students but it is impossible to create a student with more than one project. By doing so the API creates One to Many and Many to One relations.

### Installation Requirements
- IntelijIDE
- PostMan (Or any other API to send requests)
- PostgreSQL

### Instructions
First, open your PostgreSQL app or connect PostgreSQL by terminal and create a database named student by using the code below
   ```
    CREATE DATABASE student;
   ```
Than, open the project from your IntelijIDE and go to **src/main/resources/application.properties** and set the username and password according to your PostgreSQL account and run the application to start localhost.
Than, by using the requests below you can manage both tables by sending them.
  #### Post Requests
  - POST http://localhost:8080/api/v1/project
    - Structure of the body request must be
        - ```
          {
            "pname": "ProjectName",
            "instructorName": "InstructorName",
            "courseName": "CourseName",
            "students": [{
                  "name": "StudentName",
                  "email": "studentmail@mail.com",
                  "dob": "YYYY-MM-DD"
                  },
                  {
                  "name": "StudentName2",
                  "email": "studentmail2@mail.com",
                  "dob": "YYYY-MM-DD"
                  }]
          }
          ```
  - POST http://localhost:8080/api/v1/student
  - Structure of the body request must be
      - ```
        {
          "name": "StudentName",
          "email": "studentmail@mail.com",
          "dob": "YYYY-MM-DD"
        }
        ```
  #### Delete Requests
  - DELETE http://localhost:8080/api/v1/project/pid (Please don't forget that pid is the ID of the Project you want to delete so you should write a numeric value instead of pid)
  - DELETE http://localhost:8080/api/v1/student/sid (Please don't forget that sid is the ID of the Student you want to delete so you should write a numeric value instead of sid) 
  #### Put Requests to Update Tables
  - PUT http://localhost:8080/api/v1/project/pid?cname=NewCourseName&instructorname=NewInstructorName (This request is to change name of the project's course and instructor for the given project with pid)
  - PUT http://localhost:8080/api/v1/project/d/pid (This request adds a new student to project with pid)
    - Structure of the body request must be same with the post request of the student table
  - PUT http://localhost:8080/api/v1/project/c/pid?sid=SIDoftheStudent (This request assigns the already existing student without project with sid to project with pid)
  - PUT http://localhost:8080/api/v1/student/sid?name=NewName&email=NewEmail (This request updates name and email variables of the student with sid)
