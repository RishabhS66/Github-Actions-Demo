# Bank Project

This is a Maven project to simulate some basic features of a bank, like account creation, money deposit and withdrawal and transferring of money between 2 accounts.  
To run the project, please follow the steps mentioned below -

1. Using MAVEN : <br>
  a) If you have Maven, you can simply run **mvn clean install** in the Terminal, at the folder where pom.xml file is present. This will run all the tests, and will create a "target" folder. The "target" folder will contain the JAR file. <br>
  b) You can run the JAR file by using command **java -jar sahajBanking-1.0-SNAPSHOT.jar \<filePath\>**. The \<filePath\> parameter, if provided, will run the file (should be a .txt file). The user should ensure that the file path is correct.<br>
  c) For example, to use the "Demo.txt" file, shift it in the "target" folder, move inside the "target" folder and run command **java -jar sahajBanking-1.0-SNAPSHOT.jar Demo.txt**. <br>
  d) If the parameter \<filePath\> is not provided, it will launch an interactive version of the program. 
 
 2. You can directly use the JAR file present in the repository. Refer steps 1.b) - 1.d) for the same.
