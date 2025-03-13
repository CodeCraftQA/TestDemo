TestDemo Project

📌 Overview

TestDemo is an automated testing framework designed to validate user authentication and other functionalities using Selenium WebDriver with Java and TestNG. The framework follows the Page Object Model (POM) to enhance maintainability and scalability.

🛠️ Tech Stack

Java (Automation Scripting)

Selenium WebDriver (Browser Automation)

TestNG (Test Execution & Reporting)

Apache POI (Reading/Writing Excel Files for Test Data)

Jenkins (CI/CD Integration)

Git & GitHub (Version Control)

🔧 Setup Instructions

1️⃣ Prerequisites

Ensure you have the following installed on your system:

Java (JDK 8+)

Maven

Selenium WebDriver

TestNG

Git

IntelliJ IDEA / Eclipse

2️⃣ Clone the Repository

git clone git@github.com:CodeCraftQA/TestDemo.git
cd TestDemo

3️⃣ Install Dependencies

mvn clean install

4️⃣ Run the Tests

Execute tests using TestNG:

mvn test

🚀 Features

User Authentication Tests (Signup, Login, Logout)

Negative Test Scenarios (Invalid Login, SQL Injection, Blank Fields, Invalid Email Format)

Data-Driven Testing (Excel Integration for Test Data)

Cross-Browser Testing

CI/CD Pipeline Integration (Jenkins, GitHub Actions)

📝 Test Cases Implemented

✅ Valid User Login

✅ Invalid Credentials

✅ Blank Fields Validation

✅ Invalid Email Format Handling

✅ SQL Injection Attempt

✅ Logout and Re-login

📂 Project Structure

TestDemo/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/         # Base classes
│   │   │   ├── pages/        # Page Object Model classes
│   │   ├── resources/        # Configurations, test data
│   ├── test/
│   │   ├── java/
│   │   │   ├── testcases/    # Test scripts
│── pom.xml                   # Maven dependencies
│── README.md                 # Project documentation

🔗 CI/CD Pipeline

This project is configured for Jenkins integration:

Clone the repository in Jenkins.

Run the test cases using Maven.

Generate reports using TestNG Reports.

🤝 Contributing

Feel free to fork this repository and contribute. To propose changes:

Clone the repo

Create a new branch

Commit your changes

Push to your branch and create a Pull Request

📧 Contact

For any issues or suggestions, reach out at Gauravbhardwajb96@gmail.com.

