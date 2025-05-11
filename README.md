End to End Testing Framework with Cucumber, Java, Gradle, and Selenium

## Overview

This repository contains an automated UI testing framework for web applications, built using **Cucumber**, **Java**, **Gradle**, and **Selenium WebDriver**. 
The framework implements the **Page Object Model (POM)** design pattern for maintainable and scalable test automation. Test cases are written in **Gherkin** 
syntax for readability and collaboration between technical and non-technical stakeholders.

---

## Features

- **Page Object Model**: Each web page is represented as a Java class containing locators and methods for interaction.
- **Cucumber BDD**: Test scenarios are written in Gherkin and mapped to Java step definitions.
- **Selenium WebDriver**: Automates browser actions for UI testing.
- **Gradle**: Manages dependencies and builds the project.
- **JUnit/TestNG**: Supports running tests and generating reports.
- **Test Coverage**: Includes positive, negative, and boundary test cases.

---

## Getting Started

### Prerequisites

- Java 11 or higher
- Gradle 7.x or higher
- ChromeDriver or compatible WebDriver in your PATH

### Setup

1. **Clone the repository:**
2. Install dependencies gradlew clean build
3. run a clean test using "gradlew clean test"
4. Generate report using "gradlew allureReport"
5. open the report using "allure open app/build/reports/allure-report/allureReport"

### Setup
1. Feature files are located in src/test/resources/features/.
2. Step definitions are in src/test/java/ui_qa/steps/.
3. Page Objects are in src/main/java/ui_qa/pages/.

**Sample Test Coverage**
Positive Tests: Valid login, add to cart, complete checkout.
Negative Tests: Invalid login, cart errors.

