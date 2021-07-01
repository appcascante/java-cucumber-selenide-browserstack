# java-cucumber-selenide-browserstack
Instructions before running the framework:

Pre-requisites:
  > Install java and setup envinonment variables.
  > Install maven and setup environment variables.
  > Preferrably, install Eclipse.

1. Git Clone
2. Import Maven project in eclipse.
3. Right click on the root folder > click Maven > click Update Project.
4. Open command prompt > go to the framework's root directory(upetAppAutomation).
5. run: mvn clean
6. run: mvn compile

To run the test, you can do either way:

  - Under testRunner package, open junitTest.java.
  - Right-click then select Run as jUnit Test.

OR

  - In command prompt, go to the project root directory.
  - run: mvn clean verify
