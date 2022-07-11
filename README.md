
This is a E2E Rest API testing framework for sample endpoints available in https://restful-booker.herokuapp.com/apidoc/index.html, The published APIs represent a booking system where user can create, update and cancel bookings.

Tests are written using a combination of Java, RestAssured, TestNG & Maven.

Technology Stack
Java
Maven
RestAssured
TestNG
Prerequisites
Java 1.8 - Java Dev Kit
Maven - Dependency Manager
Application Under Test
Using Restful-Booker APIs as the Application Under Test.

URL : https://restful-booker.herokuapp.com/
The project directory structure
The project follows the standard directory structure used in most of the projects:

src
  + main
    + java
      + api                       Builder implementations of all AUT apis
      + env                       methods to get/set env related configs
      + pojo                      pojos of all endpoints
      + utilities                 utility methods and constants
  + test
    + java
      + api                       tests of endpoints of AUT
    + resources
      + properties                Config properties
      + suites                    TestNG suite files
      + logback.xml               for info/debug logs
Design Patterns and Standards
This framework has followed some of industry using standards and patterns like singleton, builder and oops.

Following instructions will help you running the project. First, clone this project locally on your machine from the master branch.

Installation and Test Execution
Open the project in any IDE Eclipse/IntelliJ. Run the following command in Terminal and build the project. It will automatically download all the required dependencies and execute the tests automatically with maven-surefire-plugin

$ mvn clean install
Test Report
You can find the ReportNG reports in the following directory of the Project.

/surefire-reports/index.html - // for TestNG reports
/target/surefire-reports/html/index.html - // for ReportNG reports

** You can also run the testng suite XML > which can been configured to run specific classes/tests.

**TEST DETAILS:**

1)**BaseTest **
 - Before Test annotation used to set token before running test methods
2)**BookingTest**
 - CreateBooking Test
 - Get Booking Test
 - Update BookingTest
 - Delete Booking Test

3)**Multiple Booking Test**
 - Creation of 'n' Bookings and then validating them via get ALL bookings (list response)

4)**Negative Tests**
 - Negative test for Booking with blank name details and negative amount (FAILS)
 - Negative test to access booking which doesnt exist
 
5) **PartialUpdateVerification** 
 - Update only certain fields in existing booking using parametrised constructor of Booking pojo.

-thanks.

 
