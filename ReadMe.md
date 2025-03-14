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

Cross-Browser Testing → Under Development

CI/CD Pipeline Integration (Jenkins, GitHub Actions)

Parallel Test Execution Support

Automated Screenshot Capturing in Extent Reports

📝 Test Cases Implemented

✅ HomePage is Displayed ✅ Account Registration✅ Valid User Login✅ Invalid Credentials✅ Blank Fields Validation✅ Invalid Email Format Handling✅ SQL Injection Attempt✅ Logout and Re-login✅ API Testing For Broken Pages on HomePage

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
│   │   │   │   ├── UserData.java
│   │   ├── resources/           # Configs, test data
│   │   │   ├── testdata/        # Placeholder for test data files
│── test/
│   ├── java/
│   │   ├── testcases/           # Test cases
│   │   │   ├── TC001_Is_Home_Page_Visible.java
│   │   │   ├── TC002_SignUp_Account.java
│   │   │   ├── TC003_LoginTest.java
│   │   │   ├── TC004_Login_Invalid_Tests.java
│   │   │   ├── TC005_Test_All_Pages_Links_Working.java
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

Apache POI (Excel Data-Driven Testing)

Maven (Dependency Management)

Git & GitHub (Version Control)

Jenkins & GitHub Actions (CI/CD Pipeline)

🔄 Parallel & Cross-Browser Testing

Parallel Execution: Configured via testng.xml for faster test execution.

Cross-Browser Testing: Supports execution on Chrome, Firefox, and Edge (Under Development).

📌 Future Enhancements

✅ Integrate Extent Reports for better test reporting

✅ Complete Excel-Based Data-Driven Testing

✅ Enable Headless Browser Execution

✅ Implement API Testing Framework (Postman + RestAssured)

📬 Contact & Contributions

Contributions and improvements are welcome! Feel free to fork the repo and submit a pull request.

📧 Email: YourEmail@example.com📌 GitHub: CodeCraftQA

