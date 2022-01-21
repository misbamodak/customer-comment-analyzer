# iKhokha Tech Check

### Customer Review Analyzer
---
### Introduction

At iKhokha, we love receiving feedback from our customers - especially when it's positive :) We would like to analyze our customer online reviews and comments and the new graduate was tasked to write a Java Application that will run through all the comments and create a report with totals to be used for business intelligence and marketing. 

We currently have three metrics that we keep track of:

  - Total number of comments that are shorter than 15 characters
  - Total number of comments that refer to the "Mover" device
  - Total number of comments that refer to the "Shaker" device

The daily comments are stored in text files under the **docs** directory within the project. Each line represents a single comment by a customer. All files in the directory get analyzed and the report gets printed to the console.

#### Prerequisites
  - Git
  - JDK 1.8 or later
  - An IDE of your choice (Eclipse, IntelliJ IDEA etc)

---
### Tasks
Clone this repo and complete the 3 tasks below. Be sure to read each one carefully and make your changes to the existing code provided in the project. Once complete, you can upload it to your own repo and share the url. Alternatively you can zip it up and send via email. Good luck!

##### 1. Debugging and Logical problem solving

Unfortunately our graduate seems to have a couple of bugs in his code. Word is that the report looks like it only shows the last day's comments. Emma from marketing also mentioned that some values are too low, particularly when more than one product (Mover or Shaker) is mentioned in the same comment. Your task is to fix these bugs so that the correct values are displayed in the final report.

##### 2. Object Oriented design

We foresee that over time, a lot of new metrics will be added to our report and our list of *if* statements will become long and clunky. By utilizing your skills in Object Oriented design, rewrite the matcher algorithm to make it more extensible/pluggable for adding new metrics.

Once you've made your change, add the following additional metrics:

  - Some customers use the comments section to ask questions. Add a new metric named **QUESTIONS** that displays a count of comments that contain one on more question marks "?"

  - Marketing noticed that some of the comments are spam. Add a new total called **SPAM** to the report, which should count lines that contain a url to a web page.
 
##### 3. Concurrency

Our social media pages are becoming more popular! We expect our daily comment files to become huge, resulting in long processing times per file. The current code processes the files sequentially. Change the implementation to process them concurrently using separate threads (ie one thread per file) and then consolidate the results to print the report.

Bonus question: Bear in mind that at any given time, there can be thousands of comment files in the docs folder which might crash the app if we spawn threads uncontrollably.


###### BUILD
-----

To build and install into local repo ...

    $> mvn clean install

To run tests ...

    $> mvn clean test

To run from console ...

    $> mvn exec:java
	
	
#####OUTPUT
	RESULTS
********
SHORTER_THAN_15 : 5
QUESTIONS : 6
MOVER_MENTIONS : 11
SHAKER_MENTIONS : 8
SPAM : 3

#### Run
mvn clean install

----------
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< com.ikhokha.techcheck:Main >---------------------
[INFO] Building MAIN 1.0.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-enforcer-plugin:1.3.1:enforce (enforce-versions) @ Main ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ Main ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ Main ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1252, i.e. build is platform dependent!
[INFO] Compiling 6 source files to C:\Users\modakm\Desktop\project\customer-comment-analyzer\target\classes
[WARNING] /C:/Users/modakm/Desktop/project/customer-comment-analyzer/src/main/java/com/ikhokha/techcheck/Main.java: C:\Users\modakm\Desktop\project\customer-comment-analyzer\src\main\java\com\ikhokha\techcheck\Main.java uses unchecked or unsafe operations.
[WARNING] /C:/Users/modakm/Desktop/project/customer-comment-analyzer/src/main/java/com/ikhokha/techcheck/Main.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ Main ---
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ Main ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding Cp1252, i.e. build is platform dependent!
[INFO] Compiling 3 source files to C:\Users\modakm\Desktop\project\customer-comment-analyzer\target\test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ Main ---
[INFO] Surefire report directory: C:\Users\modakm\Desktop\project\customer-comment-analyzer\target\surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.ikhokha.techcheck.test.ContainsServiceTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.134 sec
Running com.ikhokha.techcheck.test.LengthServiceTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.002 sec
Running com.ikhokha.techcheck.test.OccurrenceServiceTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec

Results :

Tests run: 12, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ Main ---
[INFO] Building jar: C:\Users\modakm\Desktop\project\customer-comment-analyzer\target\Main-1.0.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ Main ---
[INFO] Installing C:\Users\modakm\Desktop\project\customer-comment-analyzer\target\Main-1.0.0-SNAPSHOT.jar to C:\Users\modakm\.m2\repository\com\ikhokha\techcheck\Main\1.0.0-SNAPSHOT\Main-1.0.0-SNAPSHOT.jar
[INFO] Installing C:\Users\modakm\Desktop\project\customer-comment-analyzer\pom.xml to C:\Users\modakm\.m2\repository\com\ikhokha\techcheck\Main\1.0.0-SNAPSHOT\Main-1.0.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.252 s
[INFO] Finished at: 2022-01-21T19:03:38+02:00
[INFO] ------------------------------------------------------------------------



