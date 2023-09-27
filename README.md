# Tony Skinner's Java QA automation Task

This is the submission of the candidate Tony Skinner for the Java API Webservice Quality Automation Engineer position.

As per the task specification, this has been built using a Cucumber JVM framework. It is coupled with Rest Assured for the API calls, and the Hamcrest matcher library
for assertions.

## Setup
- Open JDK 20.0.1 or higher
- Gradle 8.3 or higher

## Usage Instructions
This project has been created with Gradle as the build management system. The tests have been grouped into feature files with tags. 
You can either run these, or all of the tests at once with any of the below Gradle commands from the CLI.

```shell
gradle allTests
```

Will run all the tests

```shell
gradle addingItemTests
```

Will run the adding item tests

```shell
gradle deletingItemTests
```

Will run the deleting item tests


```shell
gradle listItemTests
```

Will run the list item tests

With the execution of these tasks, a report will also be generated in 2 formats (html and json). These can be found in the `target/CucumberReports/api` folder.