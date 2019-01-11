**Run Instructions**

For jar-files 
go in bin directory and run shopping list app by using

    java -jar ShoppingList-3.0

For source code:

1. You don’t have to install the json-parser, Maven will take care that for you
2. To compile and execute json-parser use
    mvn compile 
    mvn exec:java

This only prints “Author: Hanna Haataja”

3. After installing parser you can compile and run shopping list:

Go to 2018-11-21-release-2/project/shopping-list and run

    mvn compile
    mvn exec:java

To run test:
Go to 2018-11-21-release-2/project/json-parser and run:

    mvn test

Parser runs 18 test and all of them should pass.

To use JSON parser in your own project Maven project:
NOTE: if you have previously installed json-parser it may be cood idea to delete it from your local maven -  Go to ~/.m2/repository/fi/tamk/tiko/haataja/json-parser and delete 2.0 directory
Add dependency to your POM.xml:

    <dependency>
        <groupId>fi.tamk.tiko.haataja</groupId>
        <artifactId>json-parser</artifactId>
        <version>3.0</version>
    </dependency>

and repository where it is downloaded:

    <repositories>
        <repository>
            <id>JSONParser-mvn-repo</id>
            <url>https://raw.github.com/Haataja/JSONParser/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>