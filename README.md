
# Dataken Traning (Fresher)

## Goals

1. To be able to write modern web applications Using Angular UI framework.
2. To be able to write backend services using REST in Java.
3. To be able to write SQL queries to access data from an RDBMS.

## Topics covered
1. Core Java
2. SQL
3. REST API
4. Hibernate ORM
5. Angular UI (Includes Flex layouts & ng Material)

### Estimated time

|Topic                                                |Type        |Estimated Time (Man days)|
|-----------------------------------------------------|------------|-------------------------|
|                                                     |            |                         |
|Core Java                                            |Java/Backend|6                        |
|Core Java (Excercise)                                |Java/Backend|1                        |
|SQL (With excercise)                                 |Java/Backend|2                        |
|REST API                                             |Java/Backend|1                        |
|Hibernate                                            |Java/Backend|2                        |
|Git                                                  |Version Control|0.5                        |
|Setup Intellij and Clone the backend training project|Java/Backend|0.5                      |
|Execute the backend project locally                  |Java/Backend|1                      |
|Training excercises on DAO objects                   |Java/Backend|3                        |
|Training excercises on REST API                      |Java/Backend|3                        |
|Improving the Training excercises                    |Java/Backend|2                        |
|                                                     |            |                         |
|Angular Course Video                                 |Angular/UI  |5                        |
|ng Flex layout and ng Material                       |Angular/UI  |1                        |
|Setup VS Code and Clone the UI training project      |Angular/UI  |1                        |
|Training excercises to Integrate UI with backend     |Angular/UI  |10                       |
|                                                     |            |                         |
|                                                     |Total       |39                       |


### Core Java

* Go through the Head first java book located [here](https://drive.google.com/drive/u/0/folders/1FOSprslQsAk_9FVH2AZSqCsiP0ZEHtx_).
* Chapters 1 to 11 should be covered.
* Spend 30 minutes on a daily basis in this period to cover Java coding conventions or standards. 
  Please go through this [pdf](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf) for coding conventions.
  These coding standards should be strictly followed even during the training.
* Use an editor like Sublime / Notepad++ instead of IDE to practice the content read from the book.
  [Link](https://www.sublimetext.com/download) to download Sublime Text editor.
* Download the Java SDK 8 from [this](https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html) location.

#### Core Java Exercise

* There is a CSV (Comma separated value) file provided in [this](https://drive.google.com/file/d/1O-z_elEarltXQgWG9as2OyskxaMdTFUY/view?usp=sharing) location.
  Download the CSV file and do the following tasks using Java:
    1. Read the CSV file.
    2. Parse each row into an Object.
    3. Calculate Average sales and Average profit of each state in the CSV file.
    4. Print the output in a CSV format.
    
#### Additional resources

* Once the core java exercise is done, atleast 30 to 60 minutes should be spent on a daily basis to read advanced Java topics.
  Modern Java in Action book is placed in [this](https://drive.google.com/drive/u/0/folders/1FOSprslQsAk_9FVH2AZSqCsiP0ZEHtx_) location please read the book to understand latest java syntax.
* Below Youtube videos can be used as references
    1. https://www.youtube.com/watch?v=grEKMHGYyns
    2. https://www.youtube.com/watch?v=eIrMbAQSU34
* **Last but not least** build a habit of going through the [javadocs](https://docs.oracle.com/javase/8/docs/api/) for any modules that you are using (Ex: String, ArrayList, HashMap etc)

### SQL

* SQL can be learnt and practiced from [this](https://www.w3schools.com/sql/default.asp) w3schools tutorial.
* All the topics should be covered and practiced.

### REST API

* The modern web applications talk to the backend services via REST APIs which help transfer the data using HTTP in JSON format.
* A quick tutorial on the REST API can found [here](https://www.youtube.com/watch?v=SLwpqD8n3d0)
* Make sure to understand the different HTTP methods (GET, POST, DELETE etc).
* The backend in Dataken' platform and also in training project is built using Jersey library's REST implementation.
* To know more about Jersey please refer its official [documentation](https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest3x/index.html).

### Install IDE

* Install IntelliJ IDE from [this](https://www.jetbrains.com/idea/) link.

### Hibernate ORM

* Hibernate is an Object Relational Mapping framework which maps the Java's Objects to an RDBMS tables.
* Hibernate tutorials can be found in the below links.
  * https://www.youtube.com/watch?v=9wGz56u8JKE
  * https://www.youtube.com/watch?v=0KCKBv6rbkc
  
### Git

* Git is source code version control software. Github hosts the source code repositories in the cloud.
  Follow [this](https://www.youtube.com/watch?v=xuB1Id2Wxak) video to get a quick understanding of git & github.

### Cloning the training project

* Clone this training project locally using git from IntelliJ or from the CMD.
* From the IntelliJ add the training project as [Maven](https://maven.apache.org/) project.

### Training Project

This training project is a clone of IPL dashboard, which should display the statistics of matches played in IPL season wise (2008 - 2020).
The statistics should also be displayed based on different categories like Team wise statistics, City wise statistics and Player statistics.
You can check the IPL stats [website](https://www.iplt20.com/stats/) for reference.

* UI for this project will be built using Angular framework.
* Backend will be built using the Java REST framework (Jersey) and Hibernate ORM.

### Backend Project

* After finishing Java and SQL training, the training will focus on improving the backend project "ipl-service".
* You can execute the backend project in the IntelliJ by running its main method in the class "com.dataken.Main".
* This creates a Web server and listens for the requests on http://localhost:7777 address.
* Apart from starting the web server, this project also loads the data from ipl-matches.csv and ipl-ball-by-ball.csv files into two RDBMS tables called "MATCHES" and "DELIVERIES".
* The "MATCHES" table contains the information of each IPL match played (Refer the Match Java class for the fields).
* The "DELIVERIES" table contains the information of each ball bowled in IPL from 2008 to 2020. (Refer the Delivery Java class for the fields).
* The DELIVERIES table contains a foreign key to the MATCHES table.
* The ipl-service project has sample REST API methods and sample DAO methods.
* Follow the next section to write new REST API and DAO methods.
* The REST API methods and DAO methods should be tested using Junit tests.

### Training Exercise: New REST API methods in backend

* Write API method to fetch runs scored by each team for a given season(s).
* Write API method to fetch number of matches played in each City/Venue for a given season(s). 
* Write API method to fetch Top scorers by team for a given season(s).
* Write API method to fetch Most 100s scored by a batsman for a given season(s).
* Write API method to fetch Most 50s scored by a batsman for a given season(s).
* Write API method to fetch Fastest 50s scored by a batsman for a given season(s).
* Write API method to fetch best batting strike rate for a given season(s).
* Write API method to fetch best batting average for a given season(s).
* Write API method to fetch most maidens bowled by a bowler for a given season(s).
* Write API method to fetch most dot balls bowled by a bowler for a given season(s).
* Write API method to fetch best bowling economy by a bowler for a given season(s).
* Write API method to fetch most runs conceded in an over for a given season(s).
