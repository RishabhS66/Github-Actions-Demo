# GitHub Actions Demo using a sample Bank Interface Project

This is a Maven project to simulate some basic features of a bank, like account creation, money deposit and withdrawal and transferring of money between 2 accounts. The repo then uses GitHub Actions to verify any changes being made to the codebase.

To run the project locally, please follow the steps mentioned below -

1. Install Maven (steps [here](https://maven.apache.org/install.html)).
2. Run **mvn clean install** in the Terminal, at the folder where pom.xml file is present. This will run all the tests, and will create a "target" folder. The "target" folder will contain the JAR file.
3. Run the JAR file by using command **java -jar bankingApp-1.0-SNAPSHOT.jar \<filePath\>**. The \<filePath\> parameter, if provided, will run the file (should be a .txt file). The user should ensure that the file path is correct.<br>
For example, to use the "Demo.txt" file, shift it in the "target" folder, move inside the "target" folder and run command **java -jar bankingApp-1.0-SNAPSHOT.jar Demo.txt**. <br>
4. If the parameter \<filePath\> is not provided, it will launch an interactive version of the program. 
 
