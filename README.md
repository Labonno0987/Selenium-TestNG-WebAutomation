# UI Automation using Selenium & TestNG

##  Description

This project automates both **User** and **Admin** flows for the [Daily Finance](https://dailyfinance.roadtocareer.net/) platform using **Selenium WebDriver** and **TestNG** in Java. It follows the **Page Object Model (POM)** design pattern for maintainability and readability.  

The test cases include **positive and negative** scenarios for:

-  User Registration  
-  Password Reset  
-  Profile Update  
-  Admin Validation

The project is also integrated with **Allure Reporting** for detailed test execution insights.

---

## Technology Stack

| Tool/Library          | Purpose                     |
|------------------------|-----------------------------|
| Java (JDK 17)          | Programming Language         |
| IntelliJ IDEA          | IDE                          |
| Gradle                 | Build Tool                   |
| Selenium WebDriver     | UI Automation                |
| TestNG                 | Test Framework               |
| Allure Report          | Test Reporting               |
| Apache Commons CSV     | Reading test data            |
| Rest Assured (Optional)| API Validation Support       |
| Postman                | API Testing                  |
| Gmail API              | OTP Retrieval (if applicable)|

---

## How to Run the Project

1.  Install **JDK 17** and **IntelliJ IDEA**
2.  Clone the Repository  
   - ```git clone https://github.com/Labonno0987/Selenium-TestNG-WebAutomation```
   - ``` cd Selenium-TestNG-WebAutomation```
3. Configure Gradle in Intellij IDEA
4. Import the project in IntelliJ
5. Ensure all dependencies are configured in build.gradle(Selenium, TestNG, Allure, JavaFaker, etc.)
6. Run tests: 
  - `gradle clean test` or use IntelliJ’s **Run** button. 
7. Generate Allure report:
  - `allure serve allure-results`

## Test Case Link
   - [Click here for Positive & Negative Test cases](https://docs.google.com/spreadsheets/d/1gGUXkR6cvbsSGpQQ4GqBBdJjLu0xlGjz/edit?usp=drive_link&ouid=106851050293382559720&rtpof=true&sd=true)

## Record of Full Automation 

  - [Click here to see Automation Record](https://drive.google.com/file/d/1rhzoGyJiQZ4HqzBP9_KIshSZP_gEDHWv/view?usp=drive_link)
     
## Allure Report 
   
- **Report of Allure Overview:**

  <img width="744" alt="task-1" src="https://github.com/user-attachments/assets/80a0f7ae-a1a2-45b5-808b-83edeccece1c" />
  

- **Report of Allure Behavior:**

  <img width="744" alt="task-1" src="https://github.com/user-attachments/assets/53e16ab2-92ba-4115-b57a-9e744328f972" />
  
