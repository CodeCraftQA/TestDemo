TestDemo - Automated Testing Framework

📌 Overview

TestDemo is an automated testing framework designed to validate the core functionalities of a web application. The project is built using Java, Selenium, TestNG, and Maven and supports API Testing, Data-Driven Testing, and CI/CD Integration.

🚀 Getting Started

1️⃣ Clone the Repository

git clone git@github.com:CodeCraftQA/TestDemo.git
cd TestDemo

2️⃣ Install Dependencies

mvn clean install

3️⃣ Run the Tests

Execute tests using TestNG:

mvn test

🔥 Features

User Authentication Tests (Signup, Login, Logout)

Negative Test Scenarios (Invalid Login, SQL Injection, Blank Fields, Invalid Email Format)

Data-Driven Testing (Excel Integration for Test Data) → Under Development

Cross-Browser Testing 

CI/CD Pipeline Integration (Jenkins, GitHub Actions)

Automated Screenshot Capturing in Extent Reports

📝 Test Cases Implemented

✅ HomePage is Displayed ✅ Account Registration✅ Valid User Login✅ Invalid Credentials✅ Blank Fields Validation✅ Invalid Email Format Handling✅ SQL Injection Attempt✅ Logout and Re-login

📂 Project Structure

TestDemo/
│── .idea/                       # IDE-specific settings
│── .settings/                   # Eclipse settings
│── logs/                        # Log files
│── reports/                     # Test reports
│   ├── screenshots/             # Captured screenshots
│   ├── ExtentReport.html        # Extent Reports
│── screenshots/                 # Separate screenshots directory
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/            # Base test framework
│   │   │   │   ├── BasePage.java
│   │   │   │   ├── BaseTest.java
│   │   │   ├── pages/           # Page Object Model (POM) classes
│   │   │   │   ├── HomePage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── MyAccount.java
│   │   │   │   ├── SignUpPage.java
│   │   │   ├── utils/           # Utility classes
│   │   │   │   ├── ExtentReportManager.java
│   │   │   │   ├── ScreenshotUtil.java
│   │   │   │   ├── SeleniumDebugger.java
│   │   │   │   ├── UserData.java # Placeholder for test data files
│   │   ├── resources/           # Configs, test data
│   │   │   
│── test/
│   ├── java/
│   │   │   testcases/           # Test cases
│   │   │   ├── HomePage.java
│   │   │   ├── SignUpTest.java
│   │   │   ├── LoginTest.java
│   │   │   ├── Invalid_TestCases.java
│   │   │  
│   ├── resources/
│   │   ├── TestData.xlsx        # Excel file for test data
│── target/                      # Compiled output directory
│── .classpath                   # Eclipse classpath
│── .project                     # Eclipse project settings
│── pom.xml                      # Maven dependencies
│── ReadMe.md                    # Project documentation
│── Testing.xml                   # TestNG suite configuration

🏗️ Technologies Used

Java (Test Automation Logic)

Selenium WebDriver (UI Automation)

TestNG (Test Execution and Reporting)

Logger (Logs Management)

Apache POI (Excel Data-Driven Testing)

Maven (Dependency Management)

Git & GitHub (Version Control)

Jenkins & GitHub Actions (CI/CD Pipeline)

Cross-Browser Testing

Cross-Browser Testing: Supports execution on Chrome, Firefox, and Edge.

📌 Future Enhancements

✅ Integrate Extent Reports for better test reporting (Completed)

✅ Complete Excel-Based Data-Driven Testing

✅ Enable Headless Browser Execution (Completed)

📬 Contact & Contributions

Contributions and improvements are welcome! Feel free to fork the repo and submit a pull request.

📧 Email: GauravBhardwajb96@Gmail.com 

GitHub: CodeCraftQA


